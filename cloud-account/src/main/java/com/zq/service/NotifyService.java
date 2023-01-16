package com.zq.service;

import com.zq.enums.SendCodeEnum;
import com.zq.utils.JsonData;
import org.springframework.web.multipart.MultipartFile;

public interface NotifyService {

    /**
     * 发送短信验证码

     * @return
     */
    JsonData sendCode(SendCodeEnum sendCodeEnum, String to);


    /**
     * 校验验证码

     * @return
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to,String code);

}
