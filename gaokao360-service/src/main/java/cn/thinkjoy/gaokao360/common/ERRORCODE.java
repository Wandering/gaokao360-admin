package cn.thinkjoy.gaokao360.common;

/**
 * Created by admin on 2016/1/4.
 */
public enum  ERRORCODE {

    PARAM_NOTEXIST("0100001", "查询参数不存在"),
    IDISNOTNULL("0100002", "id不能为空"),
    RESOURCEOCCUPY("0100003", "资源被占用"),
    UPDATEEXCEPTION("0100005", "修改异常"),
    DELETEEXCEPTION("0100006", "删除异常"),
    ADDEXCEPTION("0100004", "添加异常"),
    RESOURCEISNULL("0100007", "访问资源不存在"),
    TRANSFORMATION("0100008", "转换异常");
    /** The code. */
    private final String code;

    /** The message. */
    private final String message;

    /**
     * Instantiates a new error type.
     *
     * @param code
     *            the code
     * @param message
     *            the message
     */
    private ERRORCODE(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }


}
