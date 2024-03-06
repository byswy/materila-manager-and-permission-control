package com.btp.rwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btp.rwj.domain.DO.UserPermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserPermissionMapper extends BaseMapper<UserPermission> {
    @Select("select permission from rej_user_permission where uid=#{uid}")
    List<String> getPermissionList(Long uid);
}
