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
    @Value("${wxpay.appid}")
    private String  appdId;

    /**
     * 公众号密钥
     */
    @Value("${wxpay.appsecret}")
    private String  appsecret;


    /**
     *开放平台appId
     */
    @Value("${wxopen.appid}")
    private String  openAppId;

    /**
     *开放平台appsecret
     */
    @Value("${wxopen.appsecret}")
    private String  openAppsecret;

    /**
     *开放平台回调url
     */
    @Value("${wxopen.redirect_url}")
    private String  openRedirectUrl;



    /**
     * 微信开放平台二维码链接
     */
    public static final String QR_CODE_URL="https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";


}
