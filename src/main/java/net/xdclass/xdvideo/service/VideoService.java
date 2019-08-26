package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: 杨强
 * @Date: 2019/8/25 18:53
 * @Version 1.0
 * @Discription 视频业务类接口
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video Video);

    int delete(int id);

    int save(Video video);

}
