package com.btp.rwj.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btp.rwj.domain.DO.UserPermission;
import com.btp.rwj.domain.DO.UserRole;
import com.btp.rwj.domain.PO.UserRegister;
import com.btp.rwj.domain.VO.UserVO;
import com.btp.rwj.mapper.UserMapper;
import com.btp.rwj.domain.DO.User;
import com.btp.rwj.service.IUserPermissionService;
import com.btp.rwj.service.IUserRoleService;
import com.btp.rwj.service.IUserService;
import com.btp.rwj.utils.JwtUtil;
import com.btp.rwj.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserPermissionService userPermissionService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public UserVO login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String pwd = Md5Util.md5pwd(password);
        User u = lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, pwd)
                .one();
        if (u == null) {
            return null;
        }
        //更新last_login
        Date now = new Date();
        boolean update = lambdaUpdate()
                .eq(User::getUsername, username)
                .eq(User::getPassword, pwd)
                .set(User::getLastLogin, now)
                .update();
        if (!update) {
            return null;
        }
        //封装UserVO
        UserVO userVO = BeanUtil.copyProperties(u, UserVO.class);
        Long uid = u.getId();
        List<String> roleList = userRoleService.getRoleList(uid);
        List<String> permissionList = userPermissionService.getPermissionList(uid);
        userVO.setRoles(roleList);
        userVO.setPermissions(permissionList);
        String token = JwtUtil.getToken(username, roleList, permissionList);
        userVO.setToken(token);
        return userVO;
    }

    @Override
    @Transactional
    public boolean register(UserRegister userRegister) {
        String username = userRegister.getUsername();
        boolean exists = lambdaQuery().eq(User::getUsername, username).exists();
        if (exists) {
            return false;
        }

        User user = BeanUtil.copyProperties(userRegister, User.class);
        user.setCreateBy(username);
        user.setUpdateBy(username);
        userMapper.insert(user);

        Long uid = user.getId();
        List<String> roles = userRegister.getRoles();
        List<String> permissions = userRegister.getPermissions();
        List<UserRole> ur = new ArrayList<>();
        roles.forEach(role -> ur.add(new UserRole(null, uid, role)));
        userRoleService.saveBatch(ur);
        List<UserPermission> up = new ArrayList<>();
        roles.forEach(permission -> up.add(new UserPermission(null, uid, permission)));
        userPermissionService.saveBatch(up);
        return true;
    }

//    @Override
//    public UserDetail login(User user) {
//        String pwd = user.getPwd();
//        String pwd_salt = Md5Util.md5pwd(pwd);
////        System.out.println(pwd_salt);
////        System.out.println(Md5Util.md5pwd("123456"));
//        user.setPwd(pwd_salt);
//        return userMapper.login(user);
//    }
//
//    public List<String> getPermissionsByUsername(String username) {
//        return userMapper.getPermissionsByUsername(username);
//    }
//
//    public List<String> getRolesByUsername(String username) {
//        return userMapper.getRolesByUsername(username);
//    }
}
