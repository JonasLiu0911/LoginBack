package org.example.service;

import org.example.dataobject.UserScheduleDO;
import org.example.service.model.ScheduleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    ScheduleModel getScheduleById(Integer id);

    List<UserScheduleDO> getScheduleByTelephone(String telephone);
}
