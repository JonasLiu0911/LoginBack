package org.example.service;

import org.example.dataobject.UserScheduleDO;
import org.example.error.BusinessException;
import org.example.service.model.ScheduleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    ScheduleModel getScheduleById(Integer id);

    List<UserScheduleDO> getScheduleByTelephoneForNow(String telephone);

    List<UserScheduleDO> getHistoryByTel(String telephone);

    void addSchedule(ScheduleModel scheduleModel) throws BusinessException;

    int deleteScheduleById(Integer id);

    void updateScheduleById(ScheduleModel scheduleModel) throws BusinessException;
}
