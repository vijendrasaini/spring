package com.vijendra.aspect;

import com.vijendra.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.vijendra.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>> Before EmployeeService Method >>>>>>>>>>>>>>>>>>>");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(">>>>>>>>>>>>>>>>>>> Before EmployeeService Method >>>>>>>>>>>>>>>>>>>");
    }

    @After("execution(* com.vijendra.service.*.*(..))")
    public void logAfterFinally(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( finally ) >>>>>>>>>>>>>>>>>>>");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( finally ) >>>>>>>>>>>>>>>>>>>");
    }

    @AfterThrowing("execution(* com.vijendra.service.*.*(..))")
    public void logAfterOnExceptionOnly(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( AfterThrowing ) >>>>>>>>>>>>>>>>>>>");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( AfterThrowing ) >>>>>>>>>>>>>>>>>>>");
    }

    @AfterReturning("execution(* com.vijendra.service.*.*(..))")
    public void logAfterOnlyOnSuccess(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( AfterReturning ) >>>>>>>>>>>>>>>>>>>");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(">>>>>>>>>>>>>>>>>>> After ( AfterReturning ) >>>>>>>>>>>>>>>>>>>");
    }

    @Around("execution(* com.vijendra.service.*.*(..))")
    public Employee logAfterOnlyOnSuccess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>> ( Around ) >>>>>>>>>>>>>>>>>>>");
        Object result = joinPoint.proceed();
        System.out.println(result);
        System.out.println(">>>>>>>>>>>>>>>>>>> ( Around ) >>>>>>>>>>>>>>>>>>>");
        return (Employee) result;
    }
}
