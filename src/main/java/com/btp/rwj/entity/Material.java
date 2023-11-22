package com.btp.rwj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Material {
    private Integer id;
    private String materialCode;
    private String materialName;
    private String materialType;
    private double materialNum;
    private double materialPrice;
    private boolean exist = true;
}
