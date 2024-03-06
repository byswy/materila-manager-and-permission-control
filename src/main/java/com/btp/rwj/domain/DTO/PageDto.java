package com.btp.rwj.domain.DTO;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private Long total;//总个数
    private Long pages;//总页数
    private List<T> list;

    public static <VO, PO> PageDto<VO> of(Page<PO> p, Class<VO> clas) {
        PageDto<VO> dto = new PageDto<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if (records == null) {
            dto.setList(Collections.emptyList());
        } else {
            dto.setList(BeanUtil.copyToList(records, clas));
        }
        return dto;
    }

    public static <VO, PO> PageDto<VO> of(Page<PO> p, Function<PO, VO> function) {
        PageDto<VO> dto = new PageDto<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if (records == null) {
            dto.setList(Collections.emptyList());
        } else {
            dto.setList(records.stream().map(function).collect(Collectors.toList()));
        }
        return dto;
    }
}
