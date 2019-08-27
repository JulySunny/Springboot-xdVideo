package net.xdclass.xdvideo.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.mapper.UserMapper;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 11:35
 * @Version 1.0
 * @Discription 用户服务类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;


    @Autowired
    private UserMapper userMapper;
    @Override
    public User saveWeChatUser(String code) {

        String accessTokenUrl=String.format(WeChatConfig.OPEN_ACCESS_TOKEN_URL,
                weChatConfig.getOpenAppId(),
                weChatConfig.getAppsecret(),
                code);
        //获取access_token
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (CollectionUtils.isEmpty(baseMap)){
            return null;
        }
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        User dbuser = userMapper.findByOpenid(openId);
        if (dbuser!=null){
            //更新用户,直接返回
            return dbuser;
        }
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL, accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (CollectionUtils.isEmpty(baseMap)){
            return null;
        }
        String nickname = (String)baseUserMap.get("nickname");

        Double sexTemp  = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String)baseUserMap.get("province");
        String city = (String)baseUserMap.get("city");
        String country = (String)baseUserMap.get("country");
        String headimgurl = (String)baseUserMap.get("headimgurl");
        //StringBuilder拼装字符串效率更高
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        //获取指定参数对应的值


        return null;
    }
}
