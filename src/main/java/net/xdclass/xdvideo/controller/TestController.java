package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("test")
	public String test(){
		return "hello xdclass.net111";
	}

	@Autowired
	private WeChatConfig weChatConfig;

	@RequestMapping("test_config")
	public String test_config(){
		System.out.println(weChatConfig.getAppId());
		System.out.println(weChatConfig.getAppsecret());
		return "hello xdclass.net111";
	}

	@Autowired
	private VideoMapper videoMapper;

	@RequestMapping(path = "/{vi_deo}/{id}",method = RequestMethod.GET)
	public Object testDb(@PathVariable("vi_deo")String video1){
		System.out.println(video1);
		return videoMapper.findAll();
	}
	
	
}
