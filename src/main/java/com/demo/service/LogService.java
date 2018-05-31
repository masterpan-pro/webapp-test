package com.demo.service;

import com.demo.entity.Log;

import java.util.List;

public interface LogService {

    int insert(Log log);

    int insertSelective(Log log);

    int insertList(List<Log> logs);

    int update(Log log);

    List<Log> find();
}
