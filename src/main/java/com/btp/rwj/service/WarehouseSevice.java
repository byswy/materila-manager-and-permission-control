package com.btp.rwj.service;

import com.btp.rwj.entity.Warehouse;

import java.util.List;

public interface WarehouseSevice {
    Warehouse getById(Integer id);

    List<Warehouse> getAll();

    boolean deleteById(Integer id);

    boolean save(Warehouse warehouse);

    Warehouse update(Warehouse warehouse);
}
