package org.example.service.impl;

import org.apache.catalina.User;
import org.example.dao.UserDOMapper;
import org.example.dao.UserScheduleDOMapper;
import org.example.dataobject.UserDO;
import org.example.dataobject.UserPasswordDO;
import org.example.dataobject.UserScheduleDO;
import org.example.service.ScheduleService;
import org.example.service.model.ScheduleModel;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

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
}
