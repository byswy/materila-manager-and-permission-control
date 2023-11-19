package com.btp.rwj.service.impl;

import com.btp.rwj.dao.MaterialDao;
import com.btp.rwj.service.MaterialService;
import com.btp.rwj.vo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialDao materialDao;

    @Override
    public Material getById(Integer id) {
        return materialDao.getById(id);
    }

    @Override
    public List<Material> getAll() {
        return materialDao.getAll();
    }

    @Override
    public List<String> getTypes() {
        return materialDao.getTypes();
    }

    @Override
    public boolean deleteById(Integer id) {
        return materialDao.deleteById(id) > 0;
    }

    @Override
    public boolean save(Material material) {
        return materialDao.save(material) > 0;
    }

    @Override
    public boolean update(Material material) {
        return materialDao.update(material) > 0;
    }

}
