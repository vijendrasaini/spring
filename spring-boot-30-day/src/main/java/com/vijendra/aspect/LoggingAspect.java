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
/*    @Before("within(com.vijendra.service..*)")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Intercepted : " + joinPoint.getSignature().getName());
    }*/

/*    @Before("bean(stripePaymentGateway)")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Intercepted : " + joinPoint.getSignature().getName());
    }*/
/*    @Before("execution(* com.vijendra.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>> Before EmployeeService Method >>>>>>>>>>>>>>>>>>>");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(">>>>>>>>>>>>>>>>>>> Before EmployeeService Method >>>>>>>>>>>>>>>>>>>");
    }

    @Before("execution(* com.vijendra.service.*.*(..)")
    public void logBeforePayment(JoinPoint joinPoint) {
        System.out.println("Method Intercept -> Designator : Before");
    }*/

/*    @Before("@annotation(com.vijendra.annotation.LogExecution)")
    public void logAnnotatedMethod(JoinPoint joinPoint) {
        System.out.println("Intercepted : " + joinPoint.getSignature().getName());
    }*/

/*    @After("execution(* com.vijendra.service.*.*(..))")
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
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Before");

        Object result = joinPoint.proceed();

        System.out.println("After");

        return result;
    }*/
}
