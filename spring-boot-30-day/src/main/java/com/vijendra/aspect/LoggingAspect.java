package com.vijendra.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.vijendra.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">> Before EmployeeService Method");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getThis().getClass().getName());
        System.out.println(joinPoint.getTarget().getClass().getName());
    }
}
