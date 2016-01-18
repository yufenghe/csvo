/**
 * 项目名: idget<br/>
 * 文件名: LogAspect.java<br/>
 * Copyright 2015
 */
package com.id.get.util.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类 名: LogAspect<br/>
 * 描 述: 日志切面类<br/>
 * 作 者: zhaowei<br/>
 * 创 建： 2015-3-10<br/>
 * 版 本：1.5.0<br/>
 * 
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
@Aspect
public class LogAspect {
	private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	@Around(value = "execution(public * com.id.get.service..I*Service.*(..)) and !execution(public * com.id.get.service..ISecurityService.*(..))")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		Object obj = null;
		try {
			TreeStopWatch tsw = TreeStopWatch.TREE_STOP_WATCH.get();
			String method = point.getSignature().getName();
			Object objList[] = point.getArgs();
			StringBuffer sb = new StringBuffer("(");
			for (Object o : objList) {
				sb.append(String.format("%s,", o));
			}
			String args = sb.toString();
			if (args.contains(",")) {
				args = args.substring(0, args.length() - 1);
			}
			if (tsw!=null) {
				tsw.start(String.format("method:%s%s)", method, args));
				obj = point.proceed();
				tsw.stop();
			}else{
				TreeStopWatch tswOther = new TreeStopWatch();
				tswOther.start(String.format("method:%s%s)", method, args));
				obj = point.proceed();
				tswOther.stop();
				LOG.info(tswOther.printSingleResult());
			}
		} catch (Exception e) {
			LOG.error("日志切面类异常",e);
		}
		return obj;
	}
}
