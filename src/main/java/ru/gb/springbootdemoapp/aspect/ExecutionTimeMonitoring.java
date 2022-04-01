package ru.gb.springbootdemoapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.controller.StatisticController;

@Component
@Aspect
public class ExecutionTimeMonitoring {
    private final StatisticController statisticController;

    public ExecutionTimeMonitoring(StatisticController statisticController) {
        this.statisticController = statisticController;
    }

    @Pointcut("execution(* ru.gb.springbootdemoapp.service.*.*(..))")
    private void getName() {
    }

    @Around("getName()")
    public Object getExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        Object obj = proceedingJoinPoint.proceed();

        long executionTime = System.currentTimeMillis() - begin;
        statisticController.setExecutionTime(executionTime);

        return obj;
    }
}
