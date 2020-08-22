package cn.draymonder.service.solo.impl;

import cn.draymonder.entity.bo.ProductCategory;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.solo.ProductCategoryService;
import java.util.List;
import org.simpleframework.core.annotation.Service;

/**
 * @Description:
 * @Date 2020/08/18 22:42
 * @auther Draymonder
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

  @Override
  public Result<Boolean> addProductCategory(ProductCategory productCategory) {
    return null;
  }

  @Override
  public Result<Boolean> updateProductCategory(ProductCategory productCategory) {
    return null;
  }

  @Override
  public Result<Boolean> deleteProductCategory(ProductCategory productCategory) {
    return null;
  }

  @Override
  public Result<ProductCategory> queryProductCategoryById(String id) {
    return null;
  }

  @Override
  public Result<List<ProductCategory>> queryProductCategoryList(
      ProductCategory productCategoryCondition, int pageIndex, int pageSize) {
    return null;
  }
}
