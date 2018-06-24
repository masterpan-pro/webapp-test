package com.demo.service.impl;

import com.demo.dao.LogMapper;
import com.demo.entity.Log;
import com.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Log log) {
        return logMapper.insert(log);
    }

    @Override
    public int insertSelective(Log log) {
        return logMapper.insertSelective(log);
    }

    @Override
    public int insertList(List<Log> logs) {
        return logMapper.insertList(logs);
    }

    @Override
    public int update(Log log) {
        return logMapper.update(log);
    }

    @Override
    public List<Log> find() {
        List<Long> count = jdbcTemplate.queryForList("select count(*) from log", Long.class);
        log.info("Log Count: {}", count);
        return logMapper.find();
    }
}
