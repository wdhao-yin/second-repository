package com.idwdhao.service;

import com.idwdhao.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> getUsers();

    void deleteUsers(Integer id);
}
