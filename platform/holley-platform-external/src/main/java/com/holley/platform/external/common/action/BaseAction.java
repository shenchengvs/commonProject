package com.holley.platform.external.common.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**
 * 基础struts的ACTION基类
 * 
 * @author zhouli
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {

    private final static Logger logger           = Logger.getLogger(BaseAction.class);
    private static final long   serialVersionUID = 1L;

    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public JSONObject getJsonObject() {
        // InputStream -> BufferedReader -> StringBuilder -> loop -> JSONObject.toString()
        JSONObject jsonObject = null;
        try {
            InputStream is = ServletActionContext.getRequest().getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();

            String inputStr;
            while ((inputStr = br.readLine()) != null)
                sb.append(inputStr);

            jsonObject = JSONObject.fromObject(sb.toString());
            logger.info("=====recvParam=====" + jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // protected <T> T JsonToBean(String JsonString, Class<T> clazz) {
    // JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss",
    // "yyyy-MM-dd", "yyyy-MM" }));
    // JSONObject jsonObject = JSONObject.fromObject(JsonString);
    // T entity = (T) JSONObject.toBean(jsonObject, clazz);
    // return entity;
    // }

    @Override
    public void setSession(Map<String, Object> arg0) {
        // TODO Auto-generated method stub

    }

}
