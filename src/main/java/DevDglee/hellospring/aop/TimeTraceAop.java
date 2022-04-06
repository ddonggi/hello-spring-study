package DevDglee.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimeTraceAop {

    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        return joinPoint.proceed();
    }
}
