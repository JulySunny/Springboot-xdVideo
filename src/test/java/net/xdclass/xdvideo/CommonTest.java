package net.xdclass.xdvideo;

import io.jsonwebtoken.Claims;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: 杨强
 * @Date: 2019/8/26 21:30
 * @Version 1.0
 * @Discription
 */

public class CommonTest {

    @Test
    public void testGenGeneJwt(){
        User user =new User();
        user.setId(999);
        user.setHeadImg("www.xdclass.net");
        user.setName("xd");
        String token = JwtUtils.genJsonWebToken(user);

        System.out.println(token);
    }

    @Test
    public void testCheck(){
        String token="1eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjk5OSwibmFtZSI6InhkIiwiaW1nIjoid3d3LnhkY2xhc3MubmV0IiwiaWF0IjoxNTY2ODI2MzU5LCJleHAiOjE1Njc0MzExNTl9.UwDkkm6mUMsY7kIjsqbbcqVhK9N8h7dHH_s1Q-DMB7c";
        Claims claims =JwtUtils.checkJWT(token);
        if (claims!=null){
            String name = (String) claims.get("name");
            Integer id = (Integer) claims.get("id");
            String img = (String) claims.get("img");
            System.out.println(name);
//            Assert.assertEquals("xd", name);
//            Assert.assertEquals("www.xdclass.net", img);
//            Assert.assertEquals(999L, id.longValue());
        }else {
            System.out.println("非法token");
        }
    }
}
