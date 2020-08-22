package cn.draymonder.controller;

import cn.draymonder.entity.bo.ProductCategory;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.solo.ProductCategoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 产品详情Controller
 * @Date 2020/08/22 13:45
 * @auther Draymonder
 */
@Controller
public class ProductCategoryController {

  @Autowired
  private ProductCategoryService productCategoryService;

  public Result<List<ProductCategory>> getProductCategorys(HttpServletRequest req,
      HttpServletResponse resp) {
    return productCategoryService.queryProductCategoryList(new ProductCategory(), 0, 10);
  }
}
