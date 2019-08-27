package net.xdclass.xdvideo.interceptor;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 15:13
 * @Version 1.0
 * @Discription
 */
public class LoginIntercepter implements HandlerInterceptor {

    private static  final Gson gson=new Gson();

    /**
     * 进入controller之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){

        }
        if (!StringUtils.isEmpty(token)){
            Claims claims=JwtUtils.checkJWT(token);
            if (claims!=null){
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                //*******设置request属性后,这些属性能够在controller中能够拿到*******
                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录~~~~~~"));
        return false;
    }

    /**
     * 响应数据给前端
     * @param response
     */
    public static void sendJsonMessage(HttpServletResponse response,Object object){
        response.setContentType("application/json; charset=utf-8");

        try {
            PrintWriter printWriter =response.getWriter();
            printWriter.print(gson.toJson(object));
            printWriter.close();
            //为什么要进行刷新?
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
