package org.example.error;

public enum EmBusinessError implements CommonError{
    // 通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),

    UNKNOWN_ERROR(10002, "未知错误"),

    // 20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002,"用户手机号码或密码不正确"),

    // 30000开头为日程信息相关错误定义
    SCHEDULE_NOT_EXIST(30001, "日程不存在"),
    SCHEDULE_FIND_FAIL(30002, "日程查找失败");
    ;

    private int code;
    private String msg;

    EmBusinessError(int errCode, String errMsg) {
        this.code = errCode;
        this.msg = errMsg;
    }

    @Override
    public int getErrCode() {
        return code;
    }

    @Override
    public String getErrMsg() {
        return msg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.msg = errMsg;
        return this;
    }
}
