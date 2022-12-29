package com.wechat.pay.service;

/**
 */
public class WechatApiV2Exception extends WechatApiException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String returnCode;

    protected String returnMsg;

    /**
     * 错误代码
     */
    protected String errCode;
    /**
     * 错误代码描述
     */
    protected String errCodeDes;


    public String getCode() {
        if ("SUCCESS".equals(returnCode)) {
            return errCode;
        }
        return returnCode;
    }


    @Override
    public String getMessage() {
        if ("SUCCESS".equals(returnCode)) {
            return errCodeDes;
        }
        return returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
