package net.xdclass.xdvideo.service.impl;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.dto.VideoOrderDto;
import net.xdclass.xdvideo.mapper.UserMapper;
import net.xdclass.xdvideo.mapper.VideoMapper;
import net.xdclass.xdvideo.mapper.VideoOrderMapper;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.utils.CommonUtils;
import net.xdclass.xdvideo.utils.HttpUtils;
import net.xdclass.xdvideo.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 22:17
 * @Version 1.0
 * @Discription 订单接口
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Override
    public String save(VideoOrderDto videoOrderDto) throws Exception {
        //查找视频信息
        Video video = videoMapper.findById(videoOrderDto.getVideoId());
        //查找用户信息
        User user = userMapper.findByid(videoOrderDto.getUserId());
        //生成订单
        VideoOrder videoOrder = new VideoOrder();
//        videoOrder.setTotalFee(video.getPrice()); //价格
        videoOrder.setTotalFee(1); //价格--分为单位 写死

        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());
        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        videoOrder.setDel(0);
        videoOrder.setIp(videoOrderDto.getIp());
        //订单流水号
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());
        videoOrderMapper.insert(videoOrder);

        String code_url = unifiedOrder(videoOrder);
        //获取codeUrl


        return code_url;
    }

    private String unifiedOrder(VideoOrder videoOrder) throws Exception {
        //生成签名
        SortedMap<String,String> params =new TreeMap<>();
        params.put("appid",weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str",CommonUtils.generateUUID());
//        params.put("body",videoOrder.getVideoTitle());
        params.put("body","sign");
        params.put("out_trade_no",videoOrder.getOutTradeNo());
        params.put("total_fee",videoOrder.getTotalFee().toString());
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");

        String sign= WXPayUtil.createSign(params, weChatConfig.getKey());
        params.put("sign", sign);

        //map转xml
        String payXml =WXPayUtil.mapToXml(params);
        System.out.println(payXml);

        //统一下单
        String orderStr = HttpUtils.doPost(WeChatConfig.UNIFIED_ORDER_URL, payXml, 4);
        if (StringUtils.isEmpty(orderStr)){
            return null;
        }
        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap);
        if (!CollectionUtils.isEmpty(unifiedOrderMap)){
            return unifiedOrderMap.get("code_url");
        }
        return null;
    }
}
