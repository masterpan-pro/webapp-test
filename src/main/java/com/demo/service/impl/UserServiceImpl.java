package com.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.demo.entity.User;
import com.demo.dao.UserMapper;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user){
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(User user){
        return userMapper.insertSelective(user);
    }

    @Override
    public int insertList(List<User> users){
        return userMapper.insertList(users);
    }

    @Override
    public int update(User user){
        return userMapper.update(user);
    }

    @Override
    public List<User> find() {
        return userMapper.find();
    }
}
