package net.xdclass.xdvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("net.xdclass.xdvideo.mapper")
@EnableTransactionManagement
public class XdvideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdvideoApplication.class, args);
	}
}
