package com.btp.rwj.domain.Query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQuery {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private String sortBy;
    private Boolean isAsc;

    public <T> Page<T> toMpPage(OrderItem... items) {
        Page<T> page = Page.of(pageNo, pageSize);
        if (sortBy != null) {
            page.addOrder(new OrderItem(sortBy, isAsc));
        } else {
            page.addOrder(items);
        }
        return page;
    }

    public <T> Page<T> toMpPageDefaultSortByUpdateTime() {
        return toMpPage(new OrderItem("update_time", false));
    }

    public <T> Page<T> toMpPageDefaultSortByCreateTime() {
        return toMpPage(new OrderItem("create_time", false));
    }

    public <T> Page<T> toMpPage(String defaultSortBy, Boolean isAsc) {
        return toMpPage(new OrderItem(defaultSortBy, isAsc));
    }
}
