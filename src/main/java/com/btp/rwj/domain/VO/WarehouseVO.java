package com.btp.rwj.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseVO {
    private Long id;
    private String warehouseCode;
    private String warehouseName;
    private double warehouseStock;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
}
