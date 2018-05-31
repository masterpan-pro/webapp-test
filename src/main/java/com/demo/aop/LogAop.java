package com.demo.aop;

import com.demo.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 操作日志记录aop
 */
@Slf4j
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

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
        String methodName = joinPoint.getSignature().getName();
        Object result = null;
        try {
            log.info("type:{}", operationLog.description());
            log.info("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(joinPoint.getArgs()));
            //执行目标方法
            result = joinPoint.proceed();
            log.info("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
        } catch (Throwable e) {
            log.info("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
        }
        log.info("【环绕通知中的--->后置通知】：-----------------end.----------------------");
        return result;
    }

}

