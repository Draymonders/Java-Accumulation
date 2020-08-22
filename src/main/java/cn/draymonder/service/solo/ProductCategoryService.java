package cn.draymonder.service.solo;

import cn.draymonder.entity.bo.ProductCategory;
import cn.draymonder.entity.dto.Result;
import java.util.List;

/**
 * @Description:
 * @Date 2020/08/18 22:25
 * @auther Draymonder
 */
public interface ProductCategoryService {

  Result<Boolean> addProductCategory(ProductCategory productCategory);

  Result<Boolean> updateProductCategory(ProductCategory productCategory);

  Result<Boolean> deleteProductCategory(ProductCategory productCategory);

  Result<ProductCategory> queryProductCategoryById(String id);

  Result<List<ProductCategory>> queryProductCategoryList(ProductCategory productCategoryCondition,
      int pageIndex, int pageSize);
}
