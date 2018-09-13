package com.holley.platform.external.encryptor.action;

import com.holley.platform.common.dataobject.ResultBean;
import com.holley.platform.common.dataobject.ex.IdentityAuth;
import com.holley.platform.common.dataobject.ex.UserControl;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.external.common.action.BaseAction;
import com.holley.platform.external.constants.EsamV13ErrCodeEnum;
import com.holley.platform.external.util.EsamV13Util;
import com.sun.jna.Memory;

import net.sf.json.JSONObject;

public class EsamV13Action extends BaseAction {

    private static final long serialVersionUID = 1L;
    private ResultBean        result;

    /**
     * 身份认证函数
     * 
     * @param inMeterNo:输入的分散因子,字符型,长度8字节, “0000”+表号
     * @param outRand:输出的随机数1,字符型,长度8字节
     * @param outCipherText:输出的密文,字符型,长度8字节
     * @return 0：成功，其他：失败
     */
    public String identityAuthentication() {
        ResultBean result = new ResultBean();
        JSONObject jsonObject = this.getJsonObject();

        ResultBean validResult = validParam_identityAuth(jsonObject);
        if (!validResult.isSuccess()) {
            this.result = validResult;
            return SUCCESS;
        }

        String inMeterNo = StringUtil.AddjustLength(jsonObject.getString("inMeterNo"), 16, "0");

        Memory outRand = new Memory(128);
        Memory outCipherText = new Memory(128);

        int errcode = -1;
        try {
            errcode = EsamV13Util.setIp();
            if (EsamV13ErrCodeEnum.ERR_0.getValue() != errcode) {// 连接加密机失败
                result.setSuccess(false);
                result.setMessage(EsamV13ErrCodeEnum.getText(errcode));
                result.setErrorCode(errcode);
                this.result = result;
                return SUCCESS;
            }
            errcode = EsamV13Util.identityAuthentication(inMeterNo, outRand, outCipherText);
        } catch (Exception e) {
            e.printStackTrace();

            outRand.clear();
            outCipherText.clear();

            result.setSuccess(false);
            result.setMessage("加密机函数调用失败");
            return SUCCESS;
        }
        if (EsamV13ErrCodeEnum.ERR_0.getValue() == errcode) {
            String outRandstr = outRand.getString(0);
            String outCipherTextstr = outCipherText.getString(0);
            // if (outRandstr.length() > 16) {
            // outRandstr = outRandstr.substring(0, 16);
            // }
            // if (outCipherTextstr.length() > 16) {
            // outCipherTextstr = outCipherTextstr.substring(0, 16);
            // }

            IdentityAuth data = new IdentityAuth();
            data.setInMeterNo(inMeterNo);
            data.setOutRand(outRandstr);
            data.setOutCipherText(outCipherTextstr);

            result.setSuccess(true);
            result.setData(data);
        } else {
            result.setSuccess(false);
            result.setMessage(EsamV13ErrCodeEnum.getText(errcode));
            result.setErrorCode(errcode);
        }
        outRand.clear();
        outCipherText.clear();
        this.result = result;
        return SUCCESS;
    }

    /**
     * 控制命令加密函数
     * 
     * @param inRand:输入的随机数2,字符型,长度4字节
     * @param inMeterNo:输入的分散因子,字符型,长度8字节,“0000”+表号
     * @param inEsamNo:输入的ESAM 序列号,复位信息的后8字节,字符型,长度16
     * @param inData:表示跳闸、合闸、报警等控制命令明文,字符型
     * @param outCipherText:输出的密文,字符型
     * @return
     */
    public String userControl() {
        ResultBean result = new ResultBean();
        JSONObject jsonObject = this.getJsonObject();
        if (jsonObject == null) {
            result.setSuccess(false);
            result.setMessage("参数非法");
            this.result = result;
            return SUCCESS;
        }

        ResultBean validResult = validParam_userControl(jsonObject);
        if (!validResult.isSuccess()) {
            this.result = validResult;
            return SUCCESS;
        }

        String inRand = jsonObject.getString("inRand");
        String inMeterNo = jsonObject.getString("inMeterNo");
        String inEsamNo = jsonObject.getString("inEsamNo");
        String inData = jsonObject.getString("inData");

        Memory outCipherText = new Memory(128);

        int errcode = -1;
        try {
            errcode = EsamV13Util.setIp();
            if (EsamV13ErrCodeEnum.ERR_0.getValue() != errcode) {// 连接加密机失败
                result.setSuccess(false);
                result.setMessage(EsamV13ErrCodeEnum.getText(errcode));
                result.setErrorCode(errcode);
                this.result = result;
                return SUCCESS;
            }
            inRand = StringUtil.AddjustLength(inRand.toUpperCase(), 8, "0");
            inMeterNo = StringUtil.AddjustLength(inMeterNo, 16, "0");
            inEsamNo = StringUtil.AddjustLength(inEsamNo.toUpperCase(), 16, "0");
            inData = StringUtil.AddjustLength(inData, 16, "0");
            errcode = EsamV13Util.userControl(inRand, inMeterNo, inEsamNo, inData, outCipherText);
        } catch (Exception e) {
            e.printStackTrace();

            outCipherText.clear();

            result.setSuccess(false);
            result.setMessage("加密机函数调用失败");
            return SUCCESS;
        }
        if (EsamV13ErrCodeEnum.ERR_0.getValue() == errcode) {
            String outCipherTextstr = outCipherText.getString(0);
            UserControl data = new UserControl();
            data.setInRand(inRand);
            data.setInMeterNo(inMeterNo);
            data.setInEsamNo(inEsamNo);
            data.setInData(inData);
            data.setOutCipherText(outCipherTextstr);

            result.setSuccess(true);
            result.setData(data);
        } else {
            result.setSuccess(false);
            result.setMessage(EsamV13ErrCodeEnum.getText(errcode));
            result.setErrorCode(errcode);
        }
        outCipherText.clear();
        this.result = result;
        return SUCCESS;

    }

    /**
     * 身份认证函数相关参数校验
     * 
     * @param jsonObject
     * @return
     */
    private ResultBean validParam_identityAuth(JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        if (jsonObject == null) {
            resultBean.setSuccess(false);
            resultBean.setMessage("参数非法");
            return resultBean;
        }
        String inMeterNo = jsonObject.getString("inMeterNo");
        if (StringUtil.isEmpty(inMeterNo) || inMeterNo.length() != 16) {
            resultBean.setSuccess(false);
            resultBean.setMessage("分散因子的长度为16");
            return resultBean;
        }
        resultBean.setSuccess(true);
        return resultBean;
    }

    /**
     * 用户获取控制命令密文函数相关参数校验
     * 
     * @param jsonObject
     * @return
     */
    private ResultBean validParam_userControl(JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        if (jsonObject == null) {
            resultBean.setSuccess(false);
            resultBean.setMessage("参数非法");
            return resultBean;
        }
        String inRand = jsonObject.getString("inRand");
        String inDiv = jsonObject.getString("inMeterNo");
        String inEsamno = jsonObject.getString("inEsamNo");
        String inData = jsonObject.getString("inData");
        if (StringUtil.isEmpty(inRand) || inRand.length() != 8) {
            resultBean.setSuccess(false);
            resultBean.setMessage("随机数2长度为8");
            return resultBean;
        }
        if (StringUtil.isEmpty(inDiv) || inDiv.length() != 16) {
            resultBean.setSuccess(false);
            resultBean.setMessage("分散因子的长度为16");
            return resultBean;
        }
        if (StringUtil.isEmpty(inEsamno) || inEsamno.length() != 16) {
            resultBean.setSuccess(false);
            resultBean.setMessage("ESAM序列号的长度为16");
            return resultBean;
        }
        if (StringUtil.isEmpty(inData)) {
            resultBean.setSuccess(false);
            resultBean.setMessage("跳闸或合闸控制命令明文不能为空");
            return resultBean;
        }
        resultBean.setSuccess(true);
        return resultBean;
    }

    public ResultBean getResult() {
        return result;
    }

}
