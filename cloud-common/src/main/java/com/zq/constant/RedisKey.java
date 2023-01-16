package com.zq.constant;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.constant
 * @Author: zhangq
 * @CreateTime: 2023-01-16  14:15
 * @Description: TODO
 */
public class RedisKey {

    /**
     * 验证码缓存key，第一个是类型,第二个是唯一标识比如手机号或者邮箱
     */
    public static final String CHECK_CODE_KEY = "code:%s:%s";


}
