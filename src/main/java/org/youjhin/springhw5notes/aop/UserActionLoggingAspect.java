package org.youjhin.springhw5notes.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserActionLoggingAspect {

    //private final Logger log = LoggerFactory.getLogger(this.getClass());


    //добавил logback-classic и вывод в фаил
    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggingAspect.class);


    /*
        @Pointcut("@annotation(org.youjhin.springhw5notes.aop.TrackUserAction)")
        такой pointcut можно указать если хотим логировать методы на которые будем ставить метки
     */

    // логировать все public методы в пакете services в классе NoteServiceImpl
    @Pointcut("execution(public * org.youjhin.springhw5notes.services.NoteServiceImpl.*(..))")
    public void loggableMethods() {}


    // Применяем advice вокруг выбранных методов
    @Around("loggableMethods()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        // Запись в лог перед вызовом метода
        logger.info("Вызов метода: " + joinPoint.getSignature().getName());

        try {
            Object result = joinPoint.proceed(); // Вызов целевого метода
            // Запись в лог после успешного выполнения метода
            logger.info("Метод успешно выполнен: " + joinPoint.getSignature().getName());
            return result;
        }catch (IllegalArgumentException e){
            // Логирование исключения, если метод выбросил IllegalArgumentException
            logger.error("Некорректный аргумент для: " + joinPoint.getSignature().getName());
            throw e;
        }
    }
}
