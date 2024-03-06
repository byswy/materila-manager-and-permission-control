package com.btp.rwj.controller;

import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.domain.DO.Warehouse;
import com.btp.rwj.domain.DTO.PageDto;
import com.btp.rwj.domain.Query.WarehouseQuery;
import com.btp.rwj.domain.VO.WarehouseVO;
import com.btp.rwj.service.IWarehouseSevice;
import com.btp.rwj.domain.VO.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    @Autowired
    IWarehouseSevice warehouseSevice;

    @RequirePermission({"auth_query_warehouse"})
    @RequireLogin
    @GetMapping("{id}")
    public ApiResult getById(@PathVariable("id") Long id) {
        Warehouse warehouse = warehouseSevice.getById(id);
        if (warehouse == null) {
            return ApiResult.fail("仓库查询失败");
        } else {
            return ApiResult.success(warehouse);
        }
    }

    @RequirePermission({"auth_query_warehouse"})
    @RequireLogin
    @GetMapping
    public ApiResult getByQuery(WarehouseQuery warehouseQuery) {
        PageDto<WarehouseVO> result = warehouseSevice.getByQuery(warehouseQuery);
        return ApiResult.success(result);
    }

    @RequirePermission({"auth_delete_warehouse"})
    @RequireLogin
    @DeleteMapping("{id}")
    public ApiResult deleteById(@PathVariable("id") Long id) {
        if (warehouseSevice.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库删除失败");
        }
    }

    @RequirePermission({"auth_delete_warehouse"})
    @RequireLogin
    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestParam("id") List<Long> ids) {
        if (warehouseSevice.removeBatchByIds(ids)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库批量删除失败");
        }
    }

    @RequirePermission({"auth_add_warehouse"})
    @RequireLogin
    @PostMapping
    public ApiResult save(@RequestBody Warehouse warehouse) {
        if (warehouseSevice.save(warehouse)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库保存失败");
        }
    }

    @RequirePermission({"auth_add_warehouse"})
    @RequireLogin
    @PostMapping("batch")
    public ApiResult saveBatch(@RequestBody List<Warehouse> warehouses) {
        if (warehouseSevice.saveBatch(warehouses)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库批量保存失败");
        }
    }

    @RequirePermission({"auth_update_warehouse"})
    @RequireLogin
    @PutMapping
    public ApiResult update(@RequestBody Warehouse warehouse) {
        if (warehouseSevice.updateById(warehouse)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库更新失败");
        }
    }


    @RequirePermission({"auth_update_warehouse"})
    @RequireLogin
    @PutMapping("batch")
    public ApiResult updateBatch(@RequestBody List<Warehouse> warehouses) {
        if (warehouseSevice.updateBatchById(warehouses)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("仓库批量更新失败");
        }
    }

}
