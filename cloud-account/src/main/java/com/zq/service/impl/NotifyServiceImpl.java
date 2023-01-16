package com.zq.service.impl;

import com.zq.component.SmsComponent;
import com.zq.config.SmsConfig;
import com.zq.enums.SendCodeEnum;
import com.zq.service.NotifyService;
import com.zq.utils.CheckUtil;
import com.zq.utils.CommonUtil;
import com.zq.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.service.impl
 * @Author: zhangq
 * @CreateTime: 2023-01-16  11:15
 * @Description: TODO
 */

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {


    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {


        String code = CommonUtil.getRandomCode(6);

        if(CheckUtil.isEmail(to)){
            //发送邮箱验证码  TODO

        }else if(CheckUtil.isPhone(to)){

            //发送手机验证码
            smsComponent.send(to,smsConfig.getTemplateId(),code);

        }

        return JsonData.buildSuccess();
    }
}

