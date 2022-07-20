package com.dora.assignment.aop;

import com.dora.assignment.entity.HelloWorld;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @Pointcut("@annotation(com.dora.assignment.aop.Log)")
  public void pointcut() {}

  @Before("pointcut()")
  public void logMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    StringBuilder arguments = new StringBuilder();
    for (Object arg : args) {

      if (arg instanceof HelloWorld) {
        arguments = new StringBuilder(arg.toString());
      } else arguments.append(" Type: ").append(arg.getClass()).append(" Value: ").append(arg);
    }
    logger.info("Executing service: " + methodName + " with arguments: " + arguments);
  }
}
