package org.example.service.impl;

import org.apache.catalina.User;
import org.example.dao.UserDOMapper;
import org.example.dao.UserScheduleDOMapper;
import org.example.dataobject.UserDO;
import org.example.dataobject.UserPasswordDO;
import org.example.dataobject.UserScheduleDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.ScheduleService;
import org.example.service.model.ScheduleModel;
import org.example.service.model.UserModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private UserScheduleDOMapper userScheduleDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    public ScheduleServiceImpl() {
    }

    @Override
    public ScheduleModel getScheduleById(Integer id) {
        UserScheduleDO userScheduleDO = userScheduleDOMapper.selectByPrimaryKey(id);

        if (userScheduleDO == null) {
            return null;
        }

        ScheduleModel scheduleModel = new ScheduleModel();
        BeanUtils.copyProperties(userScheduleDO, scheduleModel);

        return scheduleModel;
    }

    @Override
    public List<UserScheduleDO> getScheduleByTelephone(String telephone) {

        Integer userId = userDOMapper.selectIdByTelephone(telephone);
        List<UserScheduleDO> userScheduleDOList = userScheduleDOMapper.selectByUserId(userId);

        if (userScheduleDOList.size() == 0) {
            return null;
        }

        return userScheduleDOList;
    }

    @Override
    @Transactional
    public void addSchedule(ScheduleModel scheduleModel) throws BusinessException {
        if (scheduleModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(scheduleModel);
        if (result.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }


        UserScheduleDO userScheduleDO = convertFromModel(scheduleModel);
        userScheduleDOMapper.insertSelective(userScheduleDO);

    }

    private UserScheduleDO convertFromModel(ScheduleModel scheduleModel) {
        if (scheduleModel == null) {
            return null;
        }
        UserScheduleDO userScheduleDO = new UserScheduleDO();
        BeanUtils.copyProperties(scheduleModel, userScheduleDO);
        return userScheduleDO;
    }
}
