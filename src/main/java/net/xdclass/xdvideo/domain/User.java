package net.xdclass.xdvideo.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class User implements Serializable{

  private static final long serialVersionUID = 2382940123483532258L;
  private Integer id;
  private String openid;
  private String name;
  private String headImg;
  private String phone;
  private String sign;
  private Integer sex;
  private String city;
  private Date createTime;

}
