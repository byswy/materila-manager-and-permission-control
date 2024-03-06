package com.btp.rwj.domain.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("rwj_warehouse")
public class Warehouse {
    private Long id;//
    private String warehouseCode;
    private String warehouseName;
    private double warehouseStock;
    private Boolean deleted;//
    private Date createTime;//
    private String createBy;
    private Date updateTime;//
    private String updateBy;
}
