package net.xdclass.xdvideo.mapper;

import net.xdclass.xdvideo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 14:18
 * @Version 1.0
 * @Discription 用户dao层接口
 */
public interface UserMapper {
    /**
     * 根据openId查找
     * @param openId
     * @return
     */
    @Select("select *  from user where openid=#{openid}")
    User findByOpenid(String openid);


    /**
     * 根据主键id查找
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findByid(@Param("id") int userId);


    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);


}
