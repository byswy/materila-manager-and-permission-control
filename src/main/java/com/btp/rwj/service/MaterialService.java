package com.btp.rwj.service;

import com.btp.rwj.vo.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {

    Material getById(Integer id);

    List<Material> getAll();

    List<String> getTypes();

    boolean deleteById(Integer id);

    boolean save(Material material);

    boolean update(Material material);
}
