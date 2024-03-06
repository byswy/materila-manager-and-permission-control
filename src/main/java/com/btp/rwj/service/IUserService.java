package com.btp.rwj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btp.rwj.domain.DO.User;
import com.btp.rwj.domain.PO.UserRegister;
import com.btp.rwj.domain.VO.UserVO;

public interface IUserService extends IService<User> {
    UserVO login(User user);

    boolean register(UserRegister userRegister);
}
