package com.demo.aop;

import com.demo.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 操作日志记录aop
 */
@Slf4j
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.demo.controller.*.*(..)) && @annotation(operationLog)")
    public void pointCut(OperationLog operationLog) {
    }

    @Before("pointCut(operationLog)")
    public void logBefore(JoinPoint joinPoint, OperationLog operationLog) {
        log.info(operationLog.type());
        log.info(joinPoint.getSignature().getName() + " logBefore...");
    }

    @After("pointCut(operationLog)")
    public void logAfter(JoinPoint joinPoint, OperationLog operationLog) {
        log.info(operationLog.type());
        log.info(joinPoint.getSignature().getName() + " logAfter...");
    }

    /**
     * 获取ip
     */
    public String getIp() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

