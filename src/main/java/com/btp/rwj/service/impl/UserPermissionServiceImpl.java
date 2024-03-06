package com.btp.rwj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btp.rwj.domain.DO.UserPermission;
import com.btp.rwj.mapper.UserPermissionMapper;
import com.btp.rwj.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements IUserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<String> getPermissionList(Long uid) {
        return userPermissionMapper.getPermissionList(uid);
    }
}
