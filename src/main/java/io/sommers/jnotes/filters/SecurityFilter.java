package io.sommers.jnotes.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Skylar on 7/5/2015.
 */

@Component
public class SecurityFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(request.getClass().toString());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
