package com.idwdhao.service.impl;

import com.idwdhao.aop.Mylog;
import com.idwdhao.mapper.UserMapper;
import com.idwdhao.pojo.User;
import com.idwdhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Mylog
    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public void deleteUsers(Integer id) {
        userMapper.deleteUsers(id);
    }
}
