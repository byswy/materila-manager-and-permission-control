package com.btp.rwj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private Integer id;
    private String warehouseCode;
    private String warehouseName;
    private double warehouseStock;
    private boolean exist=true;
}
