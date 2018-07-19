package com.demo.controller;

import com.demo.base.BaseJunitTest;
import com.demo.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends BaseJunitTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void index() {
    }

    @Test
    public void test() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.find().forEach(System.out::println);
            System.out.println("----------[Mybatis一级缓存]----------");
            mapper.find().forEach(System.out::println);
        }
    }
}