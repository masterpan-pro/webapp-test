package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class Log implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 请求地址
     */
    private String remoteAddr;
    /**
     * URI
     */
    private String requestUri;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 提交参数
     */
    private String params;
    /**
     * 异常
     */
    private String exception;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateDate;
    /**
     * 结束时间
     */
    private String timeout;

}
