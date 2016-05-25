package com.id.tools.costime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {
	private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	@Around(value = "execution(public * com.id..service..I*Service.*(..)) and !execution(public * com.id..service..ISecurityService.*(..))")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		Object obj = null;
		try {
			TreeStopWatch tsw = CosttimeUtil.TREE_STOP_WATCH.get();
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
