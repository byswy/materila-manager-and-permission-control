package com.btp.rwj.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

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

    public List<Material> tolist() {
        List<Material> list = new ArrayList<>();
        list.add(new Material(id, materialCode, materialName, materialType, materialNum, materialPrice));
        return list;
    }
}
