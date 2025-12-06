package by.vadarod.smartplan.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    @Pointcut(value = "execution(* by.vadarod.smartplan.controller.*.*(..))")
    public void logAllControllers() { }

    @Before(value = "logAllControllers()")
    public void beforeAnyMethodInControllers(JoinPoint joinPoint) {
        log.info("Стартовал запрос в контроллере {}", joinPoint.getSignature().toShortString());
    }

    @Pointcut(value = "@annotation(by.vadarod.smartplan.logging.LoggingAnnotation)")
    public void logAllMethodsWithAnnotation() {
    }

    @Around(value = "logAllMethodsWithAnnotation()")
    public Object aroundAnyMethodWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Обработка запроса с аннотацией...");
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Метод {} завершён. Время выполнения: {} мс", joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
