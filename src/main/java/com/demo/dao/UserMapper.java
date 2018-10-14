package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);

    int insertSelective(@Param("user") User user);

    int insertList(@Param("users") List<User> users);

    int update(@Param("user") User user);

    List<User> find();

    /**
     * mybatis的mapper返回map结果集
     * https://segmentfault.com/a/1190000004278833
     */
    @MapKey("id")
    Map<Long, Map<String,Object>> getUserValueMap();

    /**
     * mybatis的mapper返回map结果集
     * https://segmentfault.com/a/1190000004278833
     */
    @MapKey("id")
    Map<Long, User> getUserInfoMap();

}
