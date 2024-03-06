package com.btp.rwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btp.rwj.domain.DO.Warehouse;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.WarehouseQuery;
import com.btp.rwj.domain.VO.WarehouseVO;

import java.util.List;

public interface IWarehouseSevice extends IService<Warehouse> {
    PageDto<WarehouseVO> getByQuery(WarehouseQuery warehouseQuery);
}
