package com.example.ps;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		long time = System.currentTimeMillis();
		try {
			chain.doFilter(req, resp);
		} finally {
			time = System.currentTimeMillis() - time;
			LOGGER.info("{}: {} ms ", ((HttpServletRequest) req).getRequestURI(), time);
		}
	}

	public void destroy() {
	}
}
