package com.integral.boot.Aspect;

import com.integral.boot.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "@annotation(com.integral.boot.annotation.LogAnnotation)")
    public void pint(){}


    @Around(value = "pint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        获取目标对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        获取方法
        Method method = signature.getMethod();

        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

//        获取参数名
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();

        String[] parameterNames = u.getParameterNames(method);

        Optional<LogAnnotation> optional = Optional.empty();
        Optional<Class<LogAnnotation>> aClass = Optional.of(LogAnnotation.class);
//        检查空指针
        if (annotation!=null){
            String value = annotation.value();
            System.out.println(value);
        }
//        获取参数列表
        joinPoint.getArgs();

//        获取类名
        joinPoint.getTarget().getClass().getName();
//        获取方法名
        signature.getName();

//        执行目标方法
        joinPoint.proceed();
        return null;
    }

}
