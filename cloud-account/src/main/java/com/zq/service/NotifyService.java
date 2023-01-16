package com.zq.service;

import com.zq.enums.SendCodeEnum;
import com.zq.utils.JsonData;

public interface NotifyService {

    /**
     * 发送短信验证码
     */
    JsonData sendCode(SendCodeEnum sendCodeEnum, String to);

}
