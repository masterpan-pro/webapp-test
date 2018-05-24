package com.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.demo.entity.User;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);

    int insertSelective(@Param("user") User user);

    int insertList(@Param("users") List<User> users);

    int update(@Param("user") User user);

    List<User> find();

}
