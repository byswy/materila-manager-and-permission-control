package com.btp.rwj.mapper;

import com.btp.rwj.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    List<Warehouse> getAll();

    Warehouse getByCode(String code);

    Warehouse getById(Integer id);

    List<String> getIds();

    int deleteById(Integer id);

    int delete(Warehouse warehouse);

    int save(Warehouse warehouse);

    int update(Warehouse warehouse);
}
