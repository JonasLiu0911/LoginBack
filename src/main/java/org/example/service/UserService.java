package org.example.service;

import org.apache.catalina.User;
import org.example.dataobject.UserDO;
import org.example.error.BusinessException;
import org.example.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // 通过用户id获取用户对象的方法
    UserModel getUserById(Integer id);

    // 通过用户信息telephone获取对象的方法
    UserModel getUserInfoByTelephone(String telephone);

    void updateUserNameByTelephone(UserModel userModel) throws BusinessException;
    void updateUserGenderByTelephone(UserModel userModel) throws BusinessException;
    void updateUserAgeByTelephone(UserModel userModel) throws BusinessException;

    Boolean getUserByTelephone(String telephone);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException;
}
