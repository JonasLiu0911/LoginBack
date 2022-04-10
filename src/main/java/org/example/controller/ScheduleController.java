package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.viewobject.Schedule;
import org.example.controller.viewobject.UserVO;
import org.example.dataobject.UserScheduleDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.example.service.ScheduleService;
import org.example.service.UserService;
import org.example.service.model.ScheduleModel;
import org.example.service.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("schedule")
@RequestMapping("/schedule")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ScheduleController extends BaseController {

    private static Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

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
//            System.out.println(i.getId());
//            System.out.println(i.getLatitude());
            System.out.println(i.getScheduleTitle());
//            System.out.println(i.getUserId());
            System.out.println(i.getScheduleStartTime());
        }

        return CommonReturnType.create(userScheduleDOList);
    }

    @RequestMapping(value = "/addSchedule", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType addSchedule(
            @RequestParam(name = "jsonStr") String jsonStr
    ) throws BusinessException, IOException {

        System.out.println(jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.getString("scheduleInfo"));
        System.out.println(jsonObject.getBigDecimal("latitude"));
        System.out.println(jsonObject.getLong("scheduleStartTime"));

        UserModel userModel = userService.getUserInfoByTelephone(jsonObject.getString("telephone"));
        Integer userId = userModel.getId();
        System.out.println(userId);

        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setUserId(userId);

        scheduleModel.setLongitude(jsonObject.getBigDecimal("longitude"));
        System.out.println(scheduleModel.getLongitude());

        scheduleModel.setLatitude(jsonObject.getBigDecimal("latitude"));
        System.out.println(scheduleModel.getLatitude());

        scheduleModel.setScheduleTitle(jsonObject.getString("scheduleTitle"));
        System.out.println(scheduleModel.getScheduleTitle());

        scheduleModel.setScheduleInfo(jsonObject.getString("scheduleInfo"));
        System.out.println(scheduleModel.getScheduleInfo());

        scheduleModel.setScheduleStartTime(jsonObject.getLong("scheduleStartTime"));
        System.out.println(scheduleModel.getScheduleStartTime());

        scheduleService.addSchedule(scheduleModel);
        return CommonReturnType.create(scheduleModel);

    }
}
