package com.btp.rwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btp.rwj.domain.DO.Material;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.MaterialQuery;
import com.btp.rwj.domain.VO.MaterialVO;

public interface IMaterialService extends IService<Material> {
    PageDto<MaterialVO> getByQuery(MaterialQuery materialQuery);
}
