package com.btp.rwj.mapper;

import com.btp.rwj.entity.Material;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MaterialMapper {
    List<Material> getAll();

    Material getByCode(String code);

    Material getById(int id);

    List<String> getTypes();

    List<Integer> getIds();

    int deleteById(int id);

    int delete(Material material);

    int save(Material material);

    int update(Material material);
}
