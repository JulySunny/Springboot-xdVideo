package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.utils.JwtUtils;
import net.xdclass.xdvideo.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: 杨强
 * @Date: 2019/8/26 22:44
 * @Version 1.0
 * @Discription
 */
@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;
    /**
     * 拼装扫一扫登录的url
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page" ,required = true) String accessPage) throws UnsupportedEncodingException {
        //获取开放平台重定向的地址
        String redirecturl=weChatConfig.getOpenRedirectUrl();
        //url编码
        String callbackUrl= URLEncoder.encode(redirecturl, "GBK");
        String qrcodeUrl=String.format(WeChatConfig.QR_CODE_URL,
                                        weChatConfig.getOpenAppId(), //微信开放平台的appid
                                        callbackUrl,                //微信回调我们的url
                                        accessPage);                //要返回的url
        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信登录回调接口
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {


        User user = userService.saveWeChatUser(code);
        if(user != null){
            //生成jwt
            String token = JwtUtils.genJsonWebToken(user);
            // state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转
            response.sendRedirect(state+"?token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(),"UTF-8"));
        }
    }


    /**
     * 微信支付回调
     */
    //@GetMapping("/order/callback") 不能使用get请求
    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();
        //BufferedReader是一个包装设计模式 性能更高
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb  =new StringBuffer();
        String line;
        while ((line=in.readLine())!=null){
            sb.append(line);
        }
        in.close();
        inputStream.close();
        Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(callbackMap.toString());

    }

}
