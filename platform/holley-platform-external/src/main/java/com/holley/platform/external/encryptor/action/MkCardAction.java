package com.holley.platform.external.encryptor.action;

import com.holley.platform.common.dataobject.ResultBean;
import com.holley.platform.common.util.MsgUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.external.common.action.BaseAction;
import com.holley.platform.external.util.MkCardEUtil;

import net.sf.json.JSONObject;

public class MkCardAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private ResultBean        result;

    /**
     * 电量型卡表软加密
     * 
     * @param value:密文
     */
    public String mkCdFrm() {
        ResultBean result = new ResultBean();
        JSONObject jsonObject = this.getJsonObject();

        String value = jsonObject.getString("value");
        if (StringUtil.isEmpty(value)) {
            result.setSuccess(false);
            result.setMessage("明文不能为空");
            this.result = result;
            return SUCCESS;
        }
        byte[] byteArray = MsgUtil.StringToHexF(value);
        byte[] b = new byte[byteArray.length + 7];
        for (int i = 0; i < byteArray.length; i++) {
            b[i] = byteArray[i];
        }
        MkCardEUtil.mkCdFrm(b);

        result.setSuccess(true);
        result.setData(MsgUtil.getByteToHexString(b, b.length, ""));
        System.out.println(result.getData().toString());
        this.result = result;
        return SUCCESS;
    }

    public ResultBean getResult() {
        return result;
    }

}
