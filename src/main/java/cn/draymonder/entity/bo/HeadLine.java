package cn.draymonder.entity.bo;

/**
 * @Description:
 * @Date 2020/08/17 23:14
 * @auther Draymonder
 */

import java.util.Date;

/***
 * 头条实体类
 *
 * @author draymonder
 *
 */
public class HeadLine {
  @Override
  public String toString() {
    return "HeadLine [lineId=" + lineId + ", lineName=" + lineName + ", lineLink=" + lineLink + ", lineImg="
        + lineImg + ", priority=" + priority + ", enableStatus=" + enableStatus + ", createTime=" + createTime
        + ", lastEditTime=" + lastEditTime + ", getLineId()=" + getLineId() + ", getLineName()=" + getLineName()
        + ", getLineLink()=" + getLineLink() + ", getLineImg()=" + getLineImg() + ", getPriority()="
        + getPriority() + ", getEnableStatus()=" + getEnableStatus() + ", getCreateTime()=" + getCreateTime()
        + ", getLastEditTime()=" + getLastEditTime() + ", getClass()=" + getClass() + ", hashCode()="
        + hashCode() + ", toString()=" + super.toString() + "]";
  }

  private Long lineId;
  // 头条信息
  private String lineName;
  // 头条连接
  private String lineLink;
  // 头条图片
  private String lineImg;
  // 权重
  private Integer priority;
  // 0.不可用 1.可用
  private Integer enableStatus;

  public HeadLine() {
  }

  /***
   *
   * @param lineId
   * @param lineName
   * @param lineLink
   * @param lineImg
   * @param priority
   * @param enableStatus
   * @param createTime
   * @param lastEditTime
   */
  public HeadLine(Long lineId, String lineName, String lineLink, String lineImg, Integer priority,
      Integer enableStatus, Date createTime, Date lastEditTime) {
    super();
    this.lineId = lineId;
    this.lineName = lineName;
    this.lineLink = lineLink;
    this.lineImg = lineImg;
    this.priority = priority;
    this.enableStatus = enableStatus;
    this.createTime = createTime;
    this.lastEditTime = lastEditTime;
  }

  public Long getLineId() {
    return lineId;
  }

  public void setLineId(Long lineId) {
    this.lineId = lineId;
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  public String getLineLink() {
    return lineLink;
  }

  public void setLineLink(String lineLink) {
    this.lineLink = lineLink;
  }

  public String getLineImg() {
    return lineImg;
  }

  public void setLineImg(String lineImg) {
    this.lineImg = lineImg;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getEnableStatus() {
    return enableStatus;
  }

  public void setEnableStatus(Integer enableStatus) {
    this.enableStatus = enableStatus;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastEditTime() {
    return lastEditTime;
  }

  public void setLastEditTime(Date lastEditTime) {
    this.lastEditTime = lastEditTime;
  }

  private Date createTime;
  private Date lastEditTime;

}
