package com.btp.rwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.btp.rwj.domain.DO.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select role from rej_user_role where uid=#{uid}")
    List<String> getRoleList(Long uid);
}
