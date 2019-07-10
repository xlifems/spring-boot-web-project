package com.xlifems.backendninja.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xlifems.backendninja.repository.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		long startTime = (long) request.getAttribute("startTime");
		String url = request.getRequestURI().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}
		com.xlifems.backendninja.entity.Log log = new com.xlifems.backendninja.entity.Log(new Date(),
				auth.getDetails().toString(), username, url);
		logRepository.save(log);

		LOG.info("Url to: '" + url + " 'in: " + (System.currentTimeMillis() - startTime) + "ms");
	}

}
