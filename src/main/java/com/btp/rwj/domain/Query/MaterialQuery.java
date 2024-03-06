package com.btp.rwj.domain.Query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialQuery extends PageQuery {
    private String name;
    private String type;
    private Double maxNum;
    private Double minNum;
    private Double maxPrice;
    private Double minPrice;
}
