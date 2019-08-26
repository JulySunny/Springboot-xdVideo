package net.xdclass.xdvideo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: 杨强
 * @Date: 2019/8/26 11:37
 * @Version 1.0
 * @Discription mybatis分页插件配置
 */
@Configuration//也可以改成@Component==>>最顶级的抽象
public class MyBatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        //如果使用rowNumer,就使用offsetAsPageNum 当成pageNum使用
        p.setProperty("offsetAsPageNum","true");
        //如果使用rowNumer分页,就使用rowBoundsWithCount 当成count进行统计
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
