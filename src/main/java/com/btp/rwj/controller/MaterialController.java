package com.btp.rwj.controller;

import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.domain.DO.Material;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.MaterialQuery;
import com.btp.rwj.domain.VO.ApiResult;
import com.btp.rwj.domain.VO.MaterialVO;
import com.btp.rwj.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("material")
public class MaterialController {
    @Autowired
    private IMaterialService materialService;

    @RequirePermission({"auth_query_material"})
    @RequireLogin
    @GetMapping("{id}")
    public ApiResult getById(@PathVariable int id) {
        Material material = materialService.getById(id);
        if (material == null)
            return ApiResult.fail("物料查询失败");
        else
            return ApiResult.success(material);
    }

    @RequirePermission({"auth_query_material"})
    @RequireLogin
    @GetMapping
    public ApiResult getByQuery(MaterialQuery materialQuery) {
        PageDto<MaterialVO> result = materialService.getByQuery(materialQuery);
        return ApiResult.success(result);
    }

//    @RequirePermission({"auth_query_material"})
//    @RequireLogin
//    @GetMapping("/types")
//    public ApiResult getTypes() {
//        return ApiResult.success(materialService.getTypes());
//    }

    @RequirePermission({"auth_delete_material"})
    @RequireLogin
    @DeleteMapping("{id}")
    public ApiResult deleteById(@PathVariable("id") Long id) {
        if (materialService.removeById(id))
            return ApiResult.success();
        else {
            return ApiResult.fail("物料删除失败");
        }
    }

    @RequirePermission({"auth_delete_material"})
    @RequireLogin
    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        if (materialService.removeBatchByIds(ids)) {
            return ApiResult.success();
        }
        return ApiResult.fail("物料批量删除失败");
    }

    @RequirePermission({"auth_add_material"})
    @RequireLogin
    @PostMapping
    public ApiResult save(@RequestBody Material material) {
        if (materialService.save(material))
            return ApiResult.success();
        else {
            return ApiResult.fail("物料保存失败");
        }
    }

    @RequirePermission({"auth_add_material"})
    @RequireLogin
    @PostMapping("batch")
    public ApiResult saveBatch(@RequestBody List<Material> materials) {
        if (materialService.saveBatch(materials))
            return ApiResult.success();
        else {
            return ApiResult.fail("物料批量保存失败");
        }
    }

    @RequirePermission({"auth_update_material"})
    @RequireLogin
    @PutMapping
    public ApiResult update(@RequestBody Material material) {
        if (materialService.updateById(material))
            return ApiResult.success();
        else {
            return ApiResult.fail("物料更新失败");
        }
    }

    @RequirePermission({"auth_update_material"})
    @RequireLogin
    @PutMapping("batch")
    public ApiResult updateBatch(@RequestBody List<Material> materials) {
        if (materialService.updateBatchById(materials))
            return ApiResult.success();
        else {
            return ApiResult.fail("物料批量更新失败");
        }
    }
}
