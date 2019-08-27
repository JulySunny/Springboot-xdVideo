package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 15:30
 * @Version 1.0
 * @Discription
 */
@RestController
@RequestMapping("/user/api/v1")
public class OrderController {


    @RequestMapping("save")
    public JsonData saveOrder(){
        return JsonData.buildSuccess("下单成功");
    }
}
