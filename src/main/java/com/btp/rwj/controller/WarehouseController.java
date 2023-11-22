package com.btp.rwj.controller;

import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.entity.Warehouse;
import com.btp.rwj.service.WarehouseSevice;
import com.btp.rwj.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseSevice warehouseSevice;

    @RequirePermission({"auth_query_warehouse"})
    @RequireLogin
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable int id) {
        Warehouse warehouse = warehouseSevice.getById(id);
        if (warehouse == null) {
            return ApiResult.fail("仓库查询失败");
        } else {
            return ApiResult.success(warehouse);
        }
    }

    @RequirePermission({"auth_query_warehouse"})
    @RequireLogin
    @GetMapping("")
    public ApiResult getAll() {
        return ApiResult.success(warehouseSevice.getAll());
    }

    @RequirePermission({"auth_delete_warehouse"})
    @RequireLogin
    @DeleteMapping("")
    public ApiResult deleteById(@RequestParam("id") int id) {
        if (warehouseSevice.deleteById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("删除失败");
        }
    }

    @RequirePermission({"auth_delete_warehouse"})
    @RequireLogin
    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestParam("id") int[] ids) {
        for (Integer id : ids) {
            return deleteById(id);
        }
        return ApiResult.success();
    }

    @RequirePermission({"auth_update_warehouse"})
    @RequireLogin
    @PutMapping("")
    public ApiResult update(@RequestBody Warehouse warehouse) {
        if (warehouseSevice.update(warehouse).isExist()) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库修改失败");
        }
    }

    @RequirePermission({"auth_add_warehouse"})
    @RequireLogin
    @PostMapping("")
    public ApiResult save(Warehouse warehouse) {
        if (warehouseSevice.save(warehouse)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库添加失败");
        }
    }
}
