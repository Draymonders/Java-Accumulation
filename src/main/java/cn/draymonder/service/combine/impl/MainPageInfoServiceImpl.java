package cn.draymonder.service.combine.impl;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.bo.ProductCategory;
import cn.draymonder.entity.dto.MainPageInfoDTO;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.combine.MainPageInfoService;
import cn.draymonder.service.solo.HeadLineService;
import cn.draymonder.service.solo.ProductCategoryService;
import java.util.List;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description:
 * @Date 2020/08/18 22:45
 * @auther Draymonder
 */
@Service
public class MainPageInfoServiceImpl implements MainPageInfoService {
  @Autowired
  private HeadLineService headLineService;

  @Autowired
  private ProductCategoryService productCategoryService;


  @Override
  public Result<MainPageInfoDTO> getMainPageInfo(int pageIndex, int pageSize) {
    // 1. 获取头条信息
    HeadLine headLineCondition = new HeadLine();
    headLineCondition.setEnableStatus(1);
    Result<List<HeadLine>> headLineListResult = headLineService
        .queryHeadLine(headLineCondition, 0, 3);

    // 2. 取出首类别
    ProductCategory productCategoryCondition = new ProductCategory();
    Result<List<ProductCategory>> productCategoryListResult = productCategoryService
        .queryProductCategoryList(productCategoryCondition, 0, 10);

    Result<MainPageInfoDTO> result = mergeMainInfoResult(headLineListResult,
        productCategoryListResult);
    return result;
  }

  private Result<MainPageInfoDTO> mergeMainInfoResult(Result<List<HeadLine>> headLineListResult,
      Result<List<ProductCategory>> productCategoryListResult) {
    return null;
  }

}
