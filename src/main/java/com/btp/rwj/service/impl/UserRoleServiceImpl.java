package com.btp.rwj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btp.rwj.domain.DO.UserRole;
import com.btp.rwj.mapper.UserRoleMapper;
import com.btp.rwj.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getRoleList(Long uid) {
        return userRoleMapper.getRoleList(uid);
    }
}
