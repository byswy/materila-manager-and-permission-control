package com.btp.rwj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialQuery {
    private Integer pageNum = 1;
    private Integer page;
}
