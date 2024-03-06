package com.btp.rwj.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialVO {
    private Long id;
    private String materialCode;
    private String materialName;
    private String materialType;
    private double materialNum;
    private double materialPrice;
    private Long warehouseId;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
}
