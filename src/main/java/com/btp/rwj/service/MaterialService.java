package com.btp.rwj.service;

import com.btp.rwj.entity.Material;

import java.util.List;

public interface MaterialService {
    Material getById(int id);

    List<Material> getAll();

    List<String> getTypes();

    boolean deleteById(int id);

    Material save(Material material);

    Material update(Material material);
}
