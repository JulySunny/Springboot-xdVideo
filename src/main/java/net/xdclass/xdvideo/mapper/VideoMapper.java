package net.xdclass.xdvideo.mapper;

import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @Author: 杨强
 * @Date: 2019/8/25 22:39
 * @Version 1.0
 * @Discription video数据访问层
 */
public interface VideoMapper {


    @Select("select * from  video")
    @Results({
            @Result(column = "cover_img",property = "coverImg")
    })
    List<Video> findAll();

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findById(int id);


//    @Update("UPDATE video SET title=#{title} WHERE id =#{id}")
    @UpdateProvider(type = VideoProvider.class,method ="updateVideo" )
    int update(Video Video);

    @Delete("DELETE FROM video WHERE id =#{id}")
    int delete(int id);

    @Insert("INSERT INTO `video` ( `title`, `summary`, " +
            "`cover_img`, `view_num`, `price`, `create_time`," +
            " `online`, `point`)" +
            "VALUES" +
            "(#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price},#{createTime}" +
            ",#{online},#{point});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Video video);
}
