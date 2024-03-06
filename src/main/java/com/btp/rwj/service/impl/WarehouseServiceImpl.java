package com.btp.rwj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btp.rwj.domain.DO.Warehouse;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.WarehouseQuery;
import com.btp.rwj.domain.VO.WarehouseVO;
import com.btp.rwj.mapper.WarehouseMapper;
import com.btp.rwj.service.IWarehouseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseSevice {
    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public PageDto<WarehouseVO> getByQuery(WarehouseQuery warehouseQuery) {
        String name = warehouseQuery.getName();
        Double minStock = warehouseQuery.getMinStock();
        Double maxStock = warehouseQuery.getMaxStock();

        Page<Warehouse> page = warehouseQuery.toMpPageDefaultSortByUpdateTime();
        Page<Warehouse> p = lambdaQuery()
                .like(name != null, Warehouse::getWarehouseName, name)
                .ge(minStock != null, Warehouse::getWarehouseStock, minStock)
                .ge(maxStock != null, Warehouse::getWarehouseStock, maxStock)
                .page(page);
        if (p == null) {
            return null;
        }
        return PageDto.of(p, WarehouseVO.class);
    }

//    @Cacheable(key = "#id")
//    @Override
//    public Warehouse getById(Integer id) {
//        return warehouseMapper.getById(id);
//    }
//
//    @Cacheable(key = "'all'")
//    @Override
//    public List<Warehouse> getAll() {
//        return warehouseMapper.getAll();
//    }
//
//    @Override
//    public boolean deleteById(Integer id) {
//        return warehouseMapper.deleteById(id) > 0;
//    }
//
//    @Override
//    public boolean save(Warehouse warehouse) {
//        return warehouseMapper.save(warehouse) > 0;
//    }
//
//    @CachePut(key = "#warehouse.id", unless = "#warehouse.exist==false")
//    @Override
//    public Warehouse update(Warehouse warehouse) {
//        if (warehouseMapper.update(warehouse) > 0) {
//            return warehouse;
//        } else {
//            warehouse.setExist(false);
//            return warehouse;
//        }
//    }
}
