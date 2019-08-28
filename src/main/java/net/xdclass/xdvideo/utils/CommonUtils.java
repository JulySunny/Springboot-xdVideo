package net.xdclass.xdvideo.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Author: 杨强
 * @Date: 2019/8/25 18:54
 * @Version 1.0
 * @Discription 常用工具类的封装 md5 ,uuid等
 */
public class CommonUtils {

    /**
     * 生成uuid 用来表示一笔订单
     *
     * @return
     */
    public static String generateUUID() {
        String uuid = UUID.randomUUID()
                .toString().replace("-", "")
                .substring(0, 32);

        return uuid;
    }

    /**
     * md5常用工具类
     *
     * @param data
     * @return
     */
//    public static String MD5(String data) {
//        try {
//            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] array = md.digest(data.getBytes("UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            for (byte item : array) {
//                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//            }
//            return sb.toString().toUpperCase();
//        } catch (Exception exception) {
//        }
//        return null;
//
//    }
    public static String MD5(String data){
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
