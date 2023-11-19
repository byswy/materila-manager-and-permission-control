package com.btp.rwj.dao;

import com.btp.rwj.vo.Material;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MaterialDao {

    Material getById(Integer id);


    List<Material> getAll();


    List<String> getTypes();

    int deleteById(Integer id);

    int save(Material material);

    int update(Material material);
}
