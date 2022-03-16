package org.example.response;

public class CommonReturnType {

    // msg：
    // success表示成功，返回 data 存储 viewobject
    // fail表示失败，返回data存储通用的错误码格式
    private int code;
    private String msg;

    // data: 正确信息viewobject或异常信息通用错误码或空
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object data) {
        return CommonReturnType.create(0, "success", data);
    }

    public static CommonReturnType create(int code, String msg) {
        CommonReturnType type = new CommonReturnType();
        type.setCode(code);
        type.setData(null);
        type.setMsg(msg);
        return type;
    }

    public static CommonReturnType create(Object data, String msg) {
        CommonReturnType type = new CommonReturnType();
        type.setCode(0);
        type.setData(data);
        type.setMsg(msg);
        return type;
    }

    public static CommonReturnType create(int code, String msg, Object data) {
        CommonReturnType type = new CommonReturnType();
        type.setCode(code);
        type.setData(data);
        type.setMsg(msg);
        return type;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
