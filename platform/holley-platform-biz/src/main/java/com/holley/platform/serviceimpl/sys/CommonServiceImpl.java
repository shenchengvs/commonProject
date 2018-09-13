package com.holley.platform.serviceimpl.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.platform.dao.common.CommonMapper;
import com.holley.platform.service.sys.CommonService;

public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonMapper commonMapper;

//    @Override
//    public int getMaxId(String code) {
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("code", code);
//        return commonMapper.createId(param);
//    }

}
