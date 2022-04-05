package org.example.controller;

import org.example.dataobject.UserScheduleDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.example.service.ScheduleService;
import org.example.service.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("schedule")
@RequestMapping("/schedule")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ScheduleController extends BaseController {

    private static Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private HttpSession session;


    /**
     * 网页测试 获取日程信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getSchedule(@RequestParam(name = "id") Integer id) throws BusinessException {
        ScheduleModel scheduleModel = scheduleService.getScheduleById(id);

        if (scheduleModel == null) {
            throw new BusinessException(EmBusinessError.SCHEDULE_NOT_EXIST);
        }

        return CommonReturnType.create(scheduleModel);
    }

    /**
     * 根据手机号码获取用户日程信息接口
     * @param telephone
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getSchedule")
    @ResponseBody
    public CommonReturnType getScheduleByTelephone(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        List<UserScheduleDO> userScheduleDOList = scheduleService.getScheduleByTelephone(telephone);

        if (userScheduleDOList.size() == 0) {
            throw new BusinessException(EmBusinessError.SCHEDULE_NOT_EXIST);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("schedule", userScheduleDOList);

        for (UserScheduleDO i : userScheduleDOList) {
            System.out.println(i.getId().getClass().getName());
            System.out.println(i.getLatitude().getClass().getName());
            System.out.println(i.getScheduleTitle().getClass().getName());
            System.out.println(i.getUserId().getClass().getName());
            System.out.println(i.getScheduleStartTime().getClass().getName());
        }

        return CommonReturnType.create(userScheduleDOList);
    }
}
