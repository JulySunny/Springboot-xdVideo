package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.dto.VideoOrderDto;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 22:17
 * @Version 1.0
 * @Discription
 */
public interface VideoOrderService {

    /**
     * 下单
     * @return
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据流水号更新订单
     * @param videOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videOrder);

    /**
     * 根据流水号更新订单
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);


}
