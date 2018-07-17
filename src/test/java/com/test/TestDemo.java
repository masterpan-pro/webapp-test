package com.test;

import com.demo.dao.UserMapper;
import com.test.base.BaseJunitTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests
 */
@WebAppConfiguration
public class TestDemo extends BaseJunitTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.find().forEach(System.out::println);
        System.out.println("----------[Mybatis一级缓存]----------");
        mapper.find().forEach(System.out::println);
    }
}
