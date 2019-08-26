package net.xdclass.xdvideo.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * 订单表
 */
@Data
public class Video implements Serializable{

  private static final long serialVersionUID = 1467884180820603908L;
  private Integer id;
  private String title;
  private String summary;
  private String coverImg;
  private Integer viewNum;
  private Integer price;
  private java.sql.Date createTime;
  private Integer online;
  private Double point;



}
