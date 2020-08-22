package cn.draymonder.service.combine;

import cn.draymonder.entity.dto.MainPageInfoDTO;
import cn.draymonder.entity.dto.Result;

public interface MainPageInfoService {

  Result<MainPageInfoDTO> getMainPageInfo(int pageIndex, int pageSize);
}
