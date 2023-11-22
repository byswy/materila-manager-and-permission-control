package com.btp.rwj.service.impl;

import com.btp.rwj.entity.Material;
import com.btp.rwj.mapper.MaterialMapper;
import com.btp.rwj.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "material")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;

    @Cacheable(key = "#id")
    @Override
    public Material getById(int id) {
        return materialMapper.getById(id);
    }

    //    @Cacheable(key = "'all'")
    @Override
    public List<Material> getAll() {
//        List<Integer> ids = materialMapper.getIds();
//        List<Material> list = new ArrayList<Material>();
//        for (int id : ids) {
//            Material material = materialMapper.getById(id);
//            list.add(material);
//        }
        return materialMapper.getAll();
    }

    //    @Cacheable(key = "")
    @Override
    public List<String> getTypes() {
        return materialMapper.getTypes();
    }

    @Override
    public boolean deleteById(int id) {
        return materialMapper.deleteById(id) > 0;
    }

    //    @Cacheable(key = "#material.id", unless = "#material.exist==false")
    @Override
    public Material save(Material material) {
        if (materialMapper.save(material) > 0) {
            material.setId(materialMapper.getByCode(material.getMaterialCode()).getId());
            return material;
        } else {
            material.setExist(false);
            return material;
        }
    }

    @CachePut(key = "#material.id", unless = "#material.exist==false")
    @Override
    public Material update(Material material) {
        if (materialMapper.update(material) > 0) {
            return material;
        } else {
            material.setExist(false);
            return material;
        }
    }

}
