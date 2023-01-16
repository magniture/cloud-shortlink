package com.zq.controller.request;

import lombok.Data;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.controller.request
 * @Author: zhangq
 * @CreateTime: 2023-01-16  12:45
 * @Description: TODO
 */

@Data
public class SendCodeRequest {

    private String captcha;

    private String to;
}
