package com.btp.rwj.controller;

import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.entity.Material;
import com.btp.rwj.service.MaterialService;
import com.btp.rwj.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @RequirePermission({"auth_query_material"})
    @RequireLogin
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable int id) {
        Material material = materialService.getById(id);
        if (material == null)
            return ApiResult.fail("物料查询失败");
        else
            return ApiResult.success(material);
    }

    @RequirePermission({"auth_query_material"})
    @RequireLogin
    @GetMapping("")
    public ApiResult getAll() {
        return ApiResult.success(materialService.getAll());
    }

    @RequirePermission({"auth_query_material"})
    @RequireLogin
    @GetMapping("/types")
    public ApiResult getTypes() {
        return ApiResult.success(materialService.getTypes());
    }

    @RequirePermission({"auth_delete_material"})
    @RequireLogin
    @DeleteMapping("")
    public ApiResult deleteById(@RequestParam("id") int id) {
        if (materialService.deleteById(id))
            return ApiResult.success();
        else {
            return ApiResult.fail("删除失败");
        }
    }

    @RequirePermission({"auth_delete_material"})
    @RequireLogin
    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestParam("ids") int[] ids) {
        for (int id : ids) {
            return deleteById(id);
        }
        return ApiResult.success();
    }

    @RequirePermission({"auth_update_material"})
    @RequireLogin
    @PutMapping("")
    public ApiResult update(@RequestBody Material material) {
        if (materialService.update(material).isExist())
            return ApiResult.success();
        else {
            return ApiResult.fail("物料修改失败");
        }
    }

    @RequirePermission({"auth_add_material"})
    @RequireLogin
    @PostMapping("")
    public ApiResult save(@RequestBody Material material) {
        if (materialService.save(material).isExist())
            return ApiResult.success();
        else {
            return ApiResult.fail("物料添加失败");
        }
    }
}
