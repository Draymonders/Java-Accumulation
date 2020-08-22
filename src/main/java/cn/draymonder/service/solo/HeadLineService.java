package cn.draymonder.service.solo;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.dto.Result;
import java.util.List;

/**
 * @Description:
 * @Date 2020/08/18 22:25
 * @auther Draymonder
 */
public interface HeadLineService {

  Result<Boolean> addHeadLine(HeadLine headLine);

  Result<Boolean> updateHeadLine(HeadLine headLine);

  Result<Boolean> deleteHead(HeadLine headLine);

  Result<HeadLine> queryHeadLineById(int id);

  Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
