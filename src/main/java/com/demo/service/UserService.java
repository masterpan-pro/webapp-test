package com.demo.service;

import com.demo.entity.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int update(User user);

    List<User> find();
}
