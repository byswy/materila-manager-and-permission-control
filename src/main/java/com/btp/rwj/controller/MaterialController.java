package com.btp.rwj.controller;

import com.btp.rwj.dao.MaterialDao;
import com.btp.rwj.service.MaterialService;
import com.btp.rwj.vo.ApiResult;
import com.btp.rwj.vo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable int id) {
        Material material = materialService.getById(id);
        if (material == null)
            return ApiResult.fail("查询失败");
        else
            return ApiResult.success(material);
    }

    @GetMapping("/")
    public ApiResult getAll() {
        return ApiResult.success(materialService.getAll());
    }

    @GetMapping("/types")
    public ApiResult getTypes() {
        return ApiResult.success(materialService.getTypes());
    }

    @DeleteMapping("/")
    public ApiResult deleteById(@RequestParam("id") int id) {
        if (materialService.deleteById(id))
            return ApiResult.success();
        else {
            return ApiResult.fail("删除失败");
        }
    }

    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestParam("id") int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
        return ApiResult.success();
    }

    @PutMapping("/")
    public ApiResult update(@RequestBody Material material) {
        if (materialService.update(material))
            return ApiResult.success();
        else {
            return ApiResult.fail("修改失败");
        }
    }

    @PostMapping("/")
    public ApiResult save(@RequestBody Material material) {
        if (materialService.save(material))
            return ApiResult.success();
        else {
            return ApiResult.fail("添加失败");
        }
    }
}
