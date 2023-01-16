package com.zq.controller.request;

import lombok.Data;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.controller.request
 * @Author: zhangq
 * @CreateTime: 2023-01-16  15:01
 * @Description: TODO
 */

@Data
public class AccountLoginRequest {

    private String phone;

    private String pwd;
}
