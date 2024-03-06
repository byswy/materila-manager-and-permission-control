package com.btp.rwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btp.rwj.domain.DO.UserPermission;

import java.util.List;

public interface IUserPermissionService extends IService<UserPermission> {
    List<String> getPermissionList(Long uid);
}
