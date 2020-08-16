package com.proy.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.proy.pma.controllers..*)")	// is the location where we want the logger to occur.
	public void definePackagePonitcuts() {
		// empty method just to name the location specified in the pointcut
	}
	
//	@After("definePackagePonitcuts()")
//	public void logAfter(JoinPoint jp) {
//		log.debug("\n \n \n");
//		log.debug("********** Before Method Execution ********** \n {}.{} () with arguments[s] = {}",
//				jp.getSignature().getDeclaringTypeName(),
//				jp.getSignature().getName(),
//				Arrays.toString(jp.getArgs()));
//		log.debug("__________________________________________________________________________________________ \n \n \n");
//		;
//	}
	
	
	@Around("definePackagePonitcuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		log.debug("\n \n \n");
		log.debug("********** Before Method Execution ********** \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.toString(jp.getArgs()));
		log.debug("__________________________________________________________________________________________ \n \n \n");
		
		Object o = null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("********** After Method Execution ********** \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				Arrays.toString(jp.getArgs()));
		log.debug("__________________________________________________________________________________________ \n \n \n");
		
		
		return o;
	}
	
}
