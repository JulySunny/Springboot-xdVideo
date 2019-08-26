package net.xdclass.xdvideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vi/video")
public class VideoController {



	@Autowired
	private VideoService videoService;


	/**
	 * 分页接口
	 * @param page 当前第几页,默认是第一页
	 * @param size 每页显示几条
	 * @return
	 *"pageNum": 1,
	 *"pageSize": 2,
	 *"size": 2,
	 *"orderBy": null,
	 *"startRow": 1,
	 *"endRow": 2,
	 *"total": 10,
	 *"pages": 5,
	 *"list": []
	 */
	@GetMapping("page")
	public Object pageVideo(@RequestParam(value = "page",defaultValue = "1") int page,
							@RequestParam(value = "size",defaultValue = "10") int size){
		PageHelper.startPage(page, size);
		List<Video> list = videoService.findAll();
		PageInfo pageInfo =new PageInfo(list);
		Map<String,Object> data=new HashMap<>();
		return pageInfo;
	}


	/**
	 * 根据Id找视频
	 * @param videoId
	 * @return
	 */
	@GetMapping("find_by_id")
	public Object findById(@RequestParam(value = "video_id",required = true) int videoId){

		return videoService.findById(videoId);
	}

	/**
	 * 根据Id删除视频
	 * @param videoId
	 * @return
	 */
	@DeleteMapping("del_by_id")
	public Object delById(@RequestParam(value = "video_id",required = true)int videoId){

		return videoService.delete(videoId);
	}


	/**
	 * 根据Id更新视频
	 * @param video
	 * @return
	 */
	@PutMapping("update_by_id")
	public Object update( @RequestBody Video video){
		return videoService.update(video);
	}


	/**
	 * 保存视频
	 * @param video
	 * @return
	 */
	@PostMapping("save")
	public Object save(@RequestBody Video video){
		return videoService.save(video);
	}


	
	
}
