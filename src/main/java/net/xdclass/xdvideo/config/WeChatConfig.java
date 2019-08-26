package net.xdclass.xdvideo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: 杨强
 * @Date: 2019/8/25 19:26
 * @Version 1.0
 * @Discription 微信配置类
 */
@Data
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    /**
     * 公众号id
     */
    @Value("${wxpay.appId}")
    private String  appdId;

    /**
     * 公众号密钥
     */
    @Value("${wxpay.appsecret}")
    private String  appsecret;
}
