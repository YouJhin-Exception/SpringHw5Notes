package org.youjhin.springhw5notes.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Pointcut для перехвата исключений в методах всего моего пакета .services


    // Применяем AfterThrowing т.е. когда броситься исключение
    @AfterThrowing(pointcut = "execution(* org.youjhin.springhw5notes.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("Исключение в методе: " + joinPoint.getSignature().getName() + " с сообщением: " + ex.getMessage(), ex);
    }
}
