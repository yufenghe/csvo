package com.id.tools.costime;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CostTimeFilter implements Filter {
	private final static Logger LOG = LoggerFactory.getLogger(CostTimeFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		TreeStopWatch tsw = new TreeStopWatch();
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRep = (HttpServletResponse) response;
		httpRep.setContentType("text/html;charset=UTF-8");
		httpRep.setCharacterEncoding("UTF-8");
		httpReq.setCharacterEncoding("UTF-8");
		String cmd = httpReq.getHeader("CMD");
		tsw.setCmd(cmd);
		try {
			CosttimeUtil.TREE_STOP_WATCH.set(tsw);
			tsw.start(String.format("cmd:%s", cmd));
			try {
				chain.doFilter(httpReq, httpRep);
			} finally {
				tsw.stop();
				LOG.info(tsw.formatResult());
			}
		} finally{
			CosttimeUtil.TREE_STOP_WATCH.remove();
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
