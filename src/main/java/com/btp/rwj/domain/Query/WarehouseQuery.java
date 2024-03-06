package com.btp.rwj.domain.Query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseQuery extends PageQuery {
    private String name;
    private Double maxStock;
    private Double minStock;
}
