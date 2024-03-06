package com.btp.rwj.domain.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("rwj_material")
public class Material {
    private Long id;//
    private String materialCode;
    private String materialName;
    private String materialType;
    private double materialNum;
    private double materialPrice;
    @TableField("wid")
    private Long warehouseId;
    private Boolean deleted;//
    private Date createTime;//
    private String createBy;
    private Date updateTime;//
    private String updateBy;
}
