package com.newcoder.toutiao.accept;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by yiyuan on 2017/7/31.
 */
@Aspect
@Component
public class LogAspect {
    private  static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution( * com.newcoder.toutiao.control.IndexControl.*(..))")
    public void beforeMethod(JoinPoint joinPoint)//jonPonit是一个切点  面向所有的服务  使用切面编程
    {
        StringBuffer sb = new StringBuffer();
        for(Object arg :joinPoint.getArgs())
        {
            sb.append("arg:"+arg.toString()+"|");
        }
        logger.info("before time"+new Date());
        logger.info("before method: "+sb);
    }
    @After("execution( * com.newcoder.toutiao.control.IndexControl.*(..))")
    public  void afterMethod(JoinPoint joinPoint)
    {
        logger.info("after time"+new Date());
        logger.info("after method: ");
    }
}

