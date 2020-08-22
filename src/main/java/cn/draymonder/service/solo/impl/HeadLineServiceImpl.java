package cn.draymonder.service.solo.impl;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.solo.HeadLineService;
import java.util.List;
import org.simpleframework.core.annotation.Service;

/**
 * @Description:
 * @Date 2020/08/18 22:30
 * @auther Draymonder
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

  @Override
  public Result<Boolean> addHeadLine(HeadLine headLine) {
    return null;
  }

  @Override
  public Result<Boolean> updateHeadLine(HeadLine headLine) {
    return null;
  }

  @Override
  public Result<Boolean> deleteHead(HeadLine headLine) {
    return null;
  }

  @Override
  public Result<HeadLine> queryHeadLineById(int id) {
    return null;
  }

  @Override
  public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex,
      int pageSize) {
    return null;
  }
}
