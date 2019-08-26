package net.xdclass.xdvideo.domain;

import java.io.Serializable;

/**
 * 章节表
 */
public class Chapter implements Serializable{

  private static final long serialVersionUID = -952182387268549355L;
  private Integer id;
  private Integer videoId;
  private String title;
  private Integer ordered;
  private java.sql.Date createTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Integer getOrdered() {
    return ordered;
  }

  public void setOrdered(Integer ordered) {
    this.ordered = ordered;
  }


  public java.sql.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Date createTime) {
    this.createTime = createTime;
  }

}
