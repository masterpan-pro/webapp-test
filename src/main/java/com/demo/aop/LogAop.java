package com.demo.aop;

import com.demo.annotation.OperationLog;
import com.demo.entity.Log;
import com.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 操作日志记录aop
 */
@Slf4j
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    /**
     * 切点
     */
    @Pointcut("execution(* com.demo.controller.*.*(..)) && @annotation(operationLog)")
    public void pointCut(OperationLog operationLog) {
    }

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     */
    @Around(value = "pointCut(operationLog)", argNames = "joinPoint,operationLog")
    public Object logAround(ProceedingJoinPoint joinPoint, OperationLog operationLog) {
        Object result = null;
        log.info("【环绕通知中的--->前置通知】");
        long startTime = System.currentTimeMillis();
        Log l = Log.builder()
                .type("info")
                .title(operationLog.description())
                .remoteAddr(request.getRemoteAddr())
                .requestUri(request.getRequestURI())
                .method(request.getMethod())
                .params(request.getParameterMap().toString())
                .operateDate(new Date())
                .build();
        logService.insertSelective(l);
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            log.info("【环绕通知中的--->返回通知】");
            l.setTimeout(String.valueOf(System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            log.info("【环绕通知中的--->异常通知】", e);
            l.setType("error");
            l.setException(e.getMessage());
            l.setTimeout(String.valueOf(System.currentTimeMillis() - startTime));
        } finally {
            logService.update(l);
        }
        return result;
    }

}

