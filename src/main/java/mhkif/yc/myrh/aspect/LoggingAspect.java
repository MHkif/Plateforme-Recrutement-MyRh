package mhkif.yc.myrh.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {



    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    @Pointcut("within(mhkif.yc.myrh.service.impl..*)"+
            " || within(mhkif.yc.myrh.controller.JobSeekerSocketController)")
    public void loggingPointCut(){
    }

    @AfterThrowing(pointcut = "loggingPointCut() && springBeanPointcut() ", throwing = "e")
    public void logAfterThrowing(Exception e) throws Throwable {
        log.info("**********");
        log.info("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'");
        log.info("**********");
    }

    @Around("loggingPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering {}.{}, with args: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Exiting {}.{}, with result: {}, in {} ms", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result, end - start);
        return result;
    }


}
