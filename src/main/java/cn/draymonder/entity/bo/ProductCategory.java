package cn.draymonder.entity.bo;

import java.util.Date;

/**
 * @Description: 商品种类
 * @Date 2020/08/17 23:15
 * @auther Draymonder
 */
public class ProductCategory {
  private Long productCategoryId;
  private Long shopId;

  public ProductCategory() {
  }

  /***
   *
   * @param productCategoryId
   * @param shopId
   * @param productCategoryName
   * @param priority
   * @param createTime
   */
  public ProductCategory(Long productCategoryId, Long shopId, String productCategoryName, Integer priority,
      Date createTime) {
    super();
    this.productCategoryId = productCategoryId;
    this.shopId = shopId;
    this.productCategoryName = productCategoryName;
    this.priority = priority;
    this.createTime = createTime;
  }

  private String productCategoryName;
  private Integer priority;
  private Date createTime;


  public Long getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getProductCategoryName() {
    return productCategoryName;
  }

  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "ProductCategory [productCategoryId=" + productCategoryId + ", shopId=" + shopId
        + ", productCategoryName=" + productCategoryName + ", priority=" + priority + ", createTime="
        + createTime + ", getProductCategoryId()=" + getProductCategoryId() + ", getShopId()=" + getShopId()
        + ", getProductCategoryName()=" + getProductCategoryName() + ", getPriority()=" + getPriority()
        + ", getCreateTime()=" + getCreateTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
        + ", toString()=" + super.toString() + "]";
  }

}
