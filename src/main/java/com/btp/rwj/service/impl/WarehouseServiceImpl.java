package com.btp.rwj.service.impl;

import com.btp.rwj.mapper.WarehouseMapper;
import com.btp.rwj.entity.Warehouse;
import com.btp.rwj.service.WarehouseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "warehouse")
public class WarehouseServiceImpl implements WarehouseSevice {
    @Autowired
    WarehouseMapper warehouseMapper;

    @Cacheable(key = "#id")
    @Override
    public Warehouse getById(Integer id) {
        return warehouseMapper.getById(id);
    }

    @Cacheable(key = "'all'")
    @Override
    public List<Warehouse> getAll() {
        return warehouseMapper.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return warehouseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean save(Warehouse warehouse) {
        return warehouseMapper.save(warehouse) > 0;
    }

    @CachePut(key = "#warehouse.id", unless = "#warehouse.exist==false")
    @Override
    public Warehouse update(Warehouse warehouse) {
        if (warehouseMapper.update(warehouse) > 0) {
            return warehouse;
        } else {
            warehouse.setExist(false);
            return warehouse;
        }
    }
}
