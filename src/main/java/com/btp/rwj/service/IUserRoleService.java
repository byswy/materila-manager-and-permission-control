package com.btp.rwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btp.rwj.domain.DO.UserPermission;
import com.btp.rwj.domain.DO.UserRole;

import java.util.List;

public interface IUserRoleService extends IService<UserRole> {
    List<String> getRoleList(Long uid);
}
