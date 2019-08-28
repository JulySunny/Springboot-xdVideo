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
    private String  appId;

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



    /**
     * 开放平台获取access_token地址
     */
    public final static String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    /**
     * 获取用户信息
     */
    public final static String OPEN_USER_INFO_URL ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";


    /**
     * 商户号id
     */
    @Value("${wxpay.mer_id}")
    private String mchId;

    /**
     * 支付key
     */
    @Value("${wxpay.key}")
    private String key;

    /**
     * 微信支付回调的url
     */
    @Value("${wxpay.callback}")
    private String payCallbackUrl;

    /**
     * 微信统一下单接口url
     */
    public static final String UNIFIED_ORDER_URL = "http://api.xdclass.net/pay/unifiedorder";
}
