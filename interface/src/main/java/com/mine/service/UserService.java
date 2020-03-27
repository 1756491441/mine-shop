package com.mine.service;

import com.mine.bean.UserInfo;

public interface UserService {
    UserInfo selectByPrimaryKey(String id);
}
