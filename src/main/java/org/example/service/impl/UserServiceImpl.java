package org.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import org.apache.catalina.User;
import org.example.dao.UserAvatarDOMapper;
import org.example.dao.UserDOMapper;
import org.example.dao.UserPasswordDOMapper;
import org.example.dataobject.UserAvatarDO;
import org.example.dataobject.UserDO;
import org.example.dataobject.UserPasswordDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private UserAvatarDOMapper userAvatarDOMapper;

    @Autowired
    private ValidatorImpl validator;

    public UserServiceImpl() {
    }

    @Override
    public UserModel getUserById(Integer id) {
        //调用userDoMapper获取到对应的用户dataObject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        if (userDO == null) {
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO, userPasswordDO);
    }

    @Override
    public UserModel getUserInfoByTelephone(String telephone) {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        return userModel;
    }

    @Override
    public String getHeadUrlByTelephone(String telephone) {

        Integer userId = userDOMapper.selectIdByTelephone(telephone);

        String headUrl = userAvatarDOMapper.selectHeadUrlByUserId(userId);

        return headUrl;
    }

    // 修改头像
    @Override
    public void updateUserHeadByTelephone(UserModel userModel) {

        String telephone = userModel.getTelephone();
        String headUrl = userModel.getHeadUrl();
        Integer userId = userDOMapper.selectIdByTelephone(telephone);

        UserAvatarDO userAvatarDO = new UserAvatarDO();
        userAvatarDO.setUserId(userId);
        userAvatarDO.setHeadUrl(headUrl);
        System.out.println(headUrl.length());

        userAvatarDOMapper.deleteByUserId(userId);
        userAvatarDOMapper.insertSelective(userAvatarDO);
    }

    // 修改昵称
    @Override
    public void updateUserNameByTelephone(UserModel userModel) {
        UserDO userDO = convertFromModel(userModel);
        userDOMapper.updateNameByTelephone(userDO);
    }

    // 修改性别
    @Override
    public void updateUserGenderByTelephone(UserModel userModel) {
        UserDO userDO = convertFromModel(userModel);
        userDOMapper.updateGenderByTelephone(userDO);
    }

    // 修改年龄
    @Override
    public void updateUserAgeByTelephone(UserModel userModel) {
        UserDO userDO = convertFromModel(userModel);
        userDOMapper.updateAgeByTelephone(userDO);
    }

    // 修改密码
    @Override
    public void updateUserPasswordById(UserModel userModel) {

        String telephone = userModel.getTelephone();

        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.updateByUserId(userPasswordDO);
    }

    //用户获取验证码时，检测是否已存在注册用户
    @Override
    public Boolean getUserByTelephone(String telephone) {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) {
            return  false;
        } else {
            return  true;
        }
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        //把 userDO 中的数据复制给 userModel
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }

    //用户注册服务的实现
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult result = validator.validate(userModel);

        if (result.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException exception) {
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    private UserDO convertFromModel(UserModel userModel) {
        // 每一层都进行判空，这样代码才处处健壮
        if(userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        // source是userModel，target是userDO，
        // 这样在copy过程中，userModel中多余的属性会被自动丢弃
        BeanUtils.copyProperties(userModel, userDO);

        return userDO;
    }

    private UserAvatarDO convertAvatarFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserAvatarDO userAvatarDO = new UserAvatarDO();
        BeanUtils.copyProperties(userModel, userAvatarDO);

        return userAvatarDO;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return  null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();

        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDO.setUserId(userModel.getId());

        return userPasswordDO;
    }

    @Override
    public UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);

        if (!StringUtils.equals(encryptPassword, userModel.getEncryptPassword())) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名或密码错误");
        }
        return userModel;
    }
}
