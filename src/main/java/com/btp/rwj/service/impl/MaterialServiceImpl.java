package com.btp.rwj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btp.rwj.domain.DO.Material;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.MaterialQuery;
import com.btp.rwj.domain.VO.MaterialVO;
import com.btp.rwj.mapper.MaterialMapper;
import com.btp.rwj.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public PageDto<MaterialVO> getByQuery(MaterialQuery materialQuery) {
        String name = materialQuery.getName();
        String type = materialQuery.getType();
        Double minNum = materialQuery.getMinNum();
        Double maxNum = materialQuery.getMaxNum();
        Double minPrice = materialQuery.getMinPrice();
        Double maxPrice = materialQuery.getMaxPrice();

        Page<Material> page = materialQuery.toMpPageDefaultSortByUpdateTime();
        Page<Material> p = lambdaQuery()
                .like(name != null, Material::getMaterialName, name)
                .like(type != null, Material::getMaterialType, type)
                .ge(minNum != null, Material::getMaterialNum, minNum)
                .ge(maxNum != null, Material::getMaterialNum, maxNum)
                .ge(minPrice != null, Material::getMaterialPrice, minPrice)
                .ge(maxPrice != null, Material::getMaterialPrice, maxPrice)
                .page(page);
        if (p == null) {
            return null;
        }
        return PageDto.of(p, MaterialVO.class);
    }

//    @Cacheable(key = "#id")
//    @Override
//    public Material getById(int id) {
//        return materialMapper.getById(id);
//    }
//
//    //    @Cacheable(key = "'all'")
//    @Override
//    public Page<List<Material>> getAll() {
//        List<Integer> ids = materialMapper.getIds();
//        List<Material> list = new ArrayList<>();
//        Page<List<Material>> page = new Page<>();
//        page.setPageSize(50);
//        int temp = page.getPageSize();
//        for (int count = 0; count < temp; count++) {
//            Material material = materialMapper.getById(ids.get(count));
//            list.add(material);
//        }
//        page.setData(list);
//        return page;
////        return materialMapper.getAll();
//    }
//
//    //    @Cacheable(key = "")
//    @Override
//    public List<String> getTypes() {
//        return materialMapper.getTypes();
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//        return materialMapper.deleteById(id) > 0;
//    }
//
//    //    @Cacheable(key = "#material.id", unless = "#material.exist==false")
//    @Override
//    public Material save(Material material) {
//        if (materialMapper.save(material) > 0) {
//            material.setId(materialMapper.getByCode(material.getMaterialCode()).getId());
//            return material;
//        } else {
//            material.setExist(false);
//            return material;
//        }
//    }
//
//    @CachePut(key = "#material.id", unless = "#material.exist==false")
//    @Override
//    public Material update(Material material) {
//        if (materialMapper.update(material) > 0) {
//            return material;
//        } else {
//            material.setExist(false);
//            return material;
//        }
//    }
}
