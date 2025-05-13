package lingo.vault.server.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lingo.vault.server.config.UserContextHolder;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Pointcut("execution(* lingo.vault.server.service..*(..)) ||execution(* lingo.vault.server.web.controller..*(..))  ")
//    @Pointcut("@within(lingo.vault.server.annotation.Loggable)")
    public void loggableClasses() {
    }

    @Before("loggableClasses()")
    public void startLog(JoinPoint joinPoint) {
        String username = UserContextHolder.getUser();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(createMessage(username, className, methodName, "--start"));
    }

    @After("loggableClasses()")
    public void endLog(JoinPoint joinPoint) {
        String username = UserContextHolder.getUser();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(createMessage(username, className, methodName, "--end"));
    }

    private String createMessage(String username, String className, String methodName, String stage) {

        return String.format("%-10s - %-20s - %-25s%s", username, className, methodName, stage);
    }
}
