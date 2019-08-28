package net.xdclass.xdvideo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoOrder implements Serializable{
  private static final long serialVersionUID = 276869350914929461L;

  private Integer id;
  private String openid;
  private String outTradeNo;
  /**
   * 0.表示未支付,1.表示已支付
   */
  private Integer state;
  private java.util.Date createTime;
  private java.util.Date notifyTime;
  /**
   * 分为单位
   */
  private Integer totalFee;
  private String nickname;
  private String headImg;
  private Integer videoId;
  private String videoTitle;
  private String videoImg;
  private Integer userId;
  private String ip;
  private Integer del;


}
