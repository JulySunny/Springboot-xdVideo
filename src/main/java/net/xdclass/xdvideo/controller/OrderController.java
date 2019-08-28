package net.xdclass.xdvideo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.dto.VideoOrderDto;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 15:30
 * @Version 1.0
 * @Discription
 */
@RestController
//@RequestMapping("/user/api/v1")
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping("add")
    public void saveOrder(@RequestParam(value = "video_id",required = true) int id,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request); //获取ip
        //int userId=request.getAttribute("user_id");
        int userId=1;
        VideoOrderDto videoOrderDto =new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(id);
        videoOrderDto.setIp(ip);

        String codeUrl = videoOrderService.save(videoOrderDto);
//        String codeUrl="www.baidu.com";
        //生成二维码
        try{
            //生成二维码配置
            Map<EncodeHintType,Object> hints =  new HashMap<>();

            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            OutputStream out =  response.getOutputStream();
            //通过流写出去的,返回值为void就行
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
