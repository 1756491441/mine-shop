package com.mine.orderweb.controller;

import com.mine.bean.UserInfo;
import com.mine.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/3/25 20:41
 **/
@RestController
@RequestMapping("order")
public class OrderController {

    @Reference
    private UserService userService;

    @GetMapping("user/info")
    public UserInfo getUserByPrimaryKey(String id){
        return userService.selectByPrimaryKey(id);
    }

}
