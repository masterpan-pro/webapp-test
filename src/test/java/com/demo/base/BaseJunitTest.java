package com.demo.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-web.xml",
        "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-activemq.xml",
        "classpath:spring/spring-redis.xml",
})
public class BaseJunitTest {
}
