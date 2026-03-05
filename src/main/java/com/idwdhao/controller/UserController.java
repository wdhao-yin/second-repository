package com.idwdhao.controller;

import com.idwdhao.aop.Mylog;
import com.idwdhao.pojo.Result;
import com.idwdhao.pojo.User;
import com.idwdhao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public Result getUsers(){
        List<User> users = userService.getUsers();
        log.info("日志输出成功！");
        return Result.success(users);
    }

    @DeleteMapping("/users/{id}")
    public Result deleteUsers(@PathVariable Integer id){
        log.info("日志输出，已删除:{}",id);
        userService.deleteUsers(id);
        return Result.success();
    }

}
