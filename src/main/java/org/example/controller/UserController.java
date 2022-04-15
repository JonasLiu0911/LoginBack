package org.example.controller;

import com.alibaba.druid.util.StringUtils;
import org.apache.catalina.User;
import org.example.controller.viewobject.RegisterVO;
import org.example.controller.viewobject.UserVO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.example.response.OtpCode;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.example.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    private static Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private HttpSession session;


    /**
     * 网页测试 获取用户信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务获取对于id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        //将核心领域模型用户对象转换为可供UI使用的viewobject
        UserVO userVO = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }


    /**
     * 网页测试 注册
     * @param registerVO
     * @param bindingResult
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    @RequestMapping(value = "/registerjson", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType registerJson(
            @Valid
            @RequestBody RegisterVO registerVO,
            BindingResult bindingResult
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        // 从Session中获取对应手机号的验证码
        // otpCode是用户填写的，inSessionOtpCode是系统生成的
        String inSessionOtpCode = (String) session.getAttribute(registerVO.getTelephone());
        Log.info("telephone: " + registerVO.getTelephone() + " inSessionOtpCode: " + inSessionOtpCode + " otpCode: " + registerVO.getOtpCode());
        if (!StringUtils.equals(registerVO.getOtpCode(), inSessionOtpCode)) {
            Log.info("短信验证码错误");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码错误");
        }

        // 类型转换，适配数据库
        int age = Integer.parseInt(registerVO.getAge());
        Byte gender = Byte.parseByte(registerVO.getGender());

        UserModel userModel = new UserModel();
        userModel.setName(registerVO.getName());
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(registerVO.getTelephone());
        userModel.setRegisterMode("byphone");
        userModel.setEncryptPassword(this.EncodeByMd5(registerVO.getTelephone()));

        userService.register(userModel);
        // 注册成功，只返回success即可
        return CommonReturnType.create(null);
    }


    /**
     * 获取用户信息接口，用于展示在个人主页
     * @param telephone
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public CommonReturnType getUserByTelephone(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        String headUrl = userService.getHeadUrlByTelephone(telephone);
        UserModel userModel = userService.getUserInfoByTelephone(telephone);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        userModel.setHeadUrl(headUrl);
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    @RequestMapping("updateUserAvatar")
    @ResponseBody
    public CommonReturnType updateAvatarByTelephone(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "headUrl") String headUrl
    ) throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setHeadUrl(headUrl);
        userModel.setTelephone(telephone);

        System.out.println(headUrl);
        System.out.println(telephone);

        userService.updateUserHeadByTelephone(userModel);
        return CommonReturnType.create(null);
    }


    /**
     * 修改用户昵称接口
     * @param telephone
     * @param name
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateUserName")
    @ResponseBody
    public CommonReturnType updateNameByTelephone(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "name") String name
    ) throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setTelephone(telephone);

        userService.updateUserNameByTelephone(userModel);
        return CommonReturnType.create(null);
    }


    /**
     * 修改用户性别接口
     * @param telephone
     * @param genderStr
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateUserGender")
    @ResponseBody
    public CommonReturnType updateGenderByTelephone(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "gender") String genderStr
    ) throws BusinessException {
        // 适配数据库
        Byte gender = Byte.parseByte(genderStr);
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setGender(gender);

        userService.updateUserGenderByTelephone(userModel);
        return CommonReturnType.create(null);
    }


    /**
     * 修改用户年龄接口
     * @param telephone
     * @param ageStr
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updateUserAge")
    @ResponseBody
    public CommonReturnType updateAgeByTelephone(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "age") String ageStr
    ) throws BusinessException {
        // 适配数据库
        int age = Integer.parseInt(ageStr);
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setAge(age);

        userService.updateUserAgeByTelephone(userModel);
        return CommonReturnType.create(null);
    }


    /**
     * 修改用户密码接口
     * @param telephone
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    @RequestMapping(value = "/updateUserPwd", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updatePwdByTelephone(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "password") String password
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setEncryptPassword(this.EncodeByMd5(password));

        userService.updateUserPasswordById(userModel);
        return CommonReturnType.create(userModel);
    }


    /**
     * 获取验证码接口
     * @param telephone
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getOtp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) throws BusinessException {

        //用户获取验证码时，检测是否已存在注册用户
        boolean hasRegistered = userService.getUserByTelephone(telephone);
        if (hasRegistered) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册");
        }

        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 100000;
        String otpCode = String.valueOf(randomInt);

        //将OTP验证码同对应用户的手机号关联，使用httpsession的方式绑定手机号与otpCode
        session = httpServletRequest.getSession();
        session.setAttribute(telephone, otpCode);

        //将OTP验证码通过短信通道发送给用户，省略
        Log.info("telephone: " + telephone + "&otpCode: " + otpCode);

        //将信息抽象为类
        OtpCode otpCodeObj = new OtpCode(telephone, otpCode);

        return CommonReturnType.create(otpCodeObj, "successGetOtpCode");
    }


    /**
     * 用户注册接口
     * @param telephone
     * @param otpCode
     * @param name
     * @param ageStr
     * @param genderStr
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "otpCode") String otpCode,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "age") String ageStr,
            @RequestParam(name = "gender") String genderStr,
            @RequestParam(name = "password") String password
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {

        boolean hasRegistered = userService.getUserByTelephone(telephone);
        if (hasRegistered) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册");
        }

        // 从Session中获取对应手机号的验证码
        // otpCode是用户填写的，inSessionOtpCode是系统生成的
        if (session == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "无效的验证码，请重新获取");
        }

        String inSessionOtpCode = (String) session.getAttribute(telephone);

        Log.info("telephone: " + telephone + " inSessionOtpCode: " + inSessionOtpCode + " otpCode: " + otpCode);

        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            Log.info("短信验证码错误");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码错误");
        }

        // 类型转换，适配数据库
        int age = Integer.parseInt(ageStr);
        Byte gender = Byte.parseByte(genderStr);

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncryptPassword(this.EncodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(userModel);
    }


    /**
     * 用户登录接口
     * @param telephone
     * @param password
     * @param type
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(
            @RequestParam(value = "telephone", required = true) String telephone,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "type", required = true) String type
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 入参校验
        if (org.apache.commons.lang3.StringUtils.isEmpty(telephone)
                || org.apache.commons.lang3.StringUtils.isEmpty(password)
                || org.apache.commons.lang3.StringUtils.isEmpty(type)
        ) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        boolean hasRegistered = userService.getUserByTelephone(telephone);
        if (!hasRegistered) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserModel userModel = null;
        // 登录
        if (StringUtils.equals(type, "login")) {
            userModel = userService.validateLogin(telephone, this.EncodeByMd5(password));
        }
        // 自动登录
        else if (StringUtils.equals(type, "autoLogin")) {
            userModel = userService.validateLogin(telephone, password);
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 将登陆凭证加入到用户登录成功的Session中
        // 切换web页面的时候，可以不用重复登录
        // 跟web相关，与Android无关，没用到
        session = httpServletRequest.getSession();
        session.setAttribute("IS_LOGIN", true);
        session.setAttribute("LOGIN_USER", userModel);

        // 登录成功，只返回success即可
        return CommonReturnType.create(userModel);
    }

    // 将UserModel转为UserVO
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    // MD5加密+BASE64编码
    public String EncodeByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr = base64en.encode(md5.digest(password.getBytes("utf-8")));
        return newstr;
    }

}
