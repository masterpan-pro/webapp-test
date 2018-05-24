package com.demo.service;

import java.util.List;
import com.demo.entity.User;
public interface UserService{

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int update(User user);

    List<User> find();
}
