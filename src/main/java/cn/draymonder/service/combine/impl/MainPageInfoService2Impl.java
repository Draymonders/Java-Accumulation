package cn.draymonder.service.combine.impl;

import cn.draymonder.entity.dto.MainPageInfoDTO;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.combine.MainPageInfoService;
import org.simpleframework.core.annotation.Service;

/**
 * @Description: 第二个MainPageInfoService实现类
 * @Date 2020/08/22 20:47
 * @auther Draymonder
 */
@Service
public class MainPageInfoService2Impl implements MainPageInfoService {

  @Override
  public Result<MainPageInfoDTO> getMainPageInfo(int pageIndex, int pageSize) {
    return null;
  }
}
