package cn.draymonder.entity.dto;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.bo.ProductCategory;
import java.util.List;

/**
 * @Description:
 * @Date 2020/08/18 22:43
 * @auther Draymonder
 */
public class MainPageInfoDTO {
  List<HeadLine> headLineList;
  List<ProductCategory> productCategorieList;

  public List<HeadLine> getHeadLineList() {
    return headLineList;
  }

  public void setHeadLineList(List<HeadLine> headLineList) {
    this.headLineList = headLineList;
  }

  public List<ProductCategory> getProductCategorieList() {
    return productCategorieList;
  }

  public void setProductCategorieList(
      List<ProductCategory> productCategorieList) {
    this.productCategorieList = productCategorieList;
  }
}
