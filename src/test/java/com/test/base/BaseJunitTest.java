package com.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-web.xml",
        "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-activemq.xml",
        "classpath:spring/spring-redis.xml",
})
public class BaseJunitTest {
}
