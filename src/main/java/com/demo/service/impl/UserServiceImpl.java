package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional("txManager")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int insertList(List<User> users) {
        return userMapper.insertList(users);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<User> find() {
        return userMapper.find();
    }

    @Override
    public void getUserMap() {
        Map<Long, Map<String, Object>> userValueMap = userMapper.getUserValueMap();
        Map<Long, User> userInfoMap = userMapper.getUserInfoMap();
        System.out.println(userValueMap);
        System.out.println(userInfoMap);
    }
}
