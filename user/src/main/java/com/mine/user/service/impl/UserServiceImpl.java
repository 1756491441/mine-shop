package com.mine.user.service.impl;

import com.mine.bean.UserInfo;
import com.mine.service.UserService;
import com.mine.user.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/3/26 10:57
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
