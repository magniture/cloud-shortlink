package com.zq.controller.request;

import lombok.Data;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.controller.request
 * @Author: zhangq
 * @CreateTime: 2023-01-16  14:11
 * @Description: TODO
 */

@Data
public class AccountRegisterRequest {

    /**
     * 头像
     */
    private String headImg;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;



    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户名
     */
    private String username;


    /**
     * 短信验证码
     */
    private String code;

}

