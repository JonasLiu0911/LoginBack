package org.example.service;

import org.example.error.BusinessException;
import org.example.service.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //通过用户id获取用户对象的方法
    public UserModel getUserById(Integer id);

    public Boolean getUserByTelephone(String telephone);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException;
}
