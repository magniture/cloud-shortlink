package com.zq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: cloud-shortlink
 * @BelongsPackage: com.zq.config
 * @Author: zhangq
 * @CreateTime: 2023-01-16  13:51
 * @Description: TODO
 */
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketname;
}