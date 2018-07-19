package com.devopstools.portal.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pubudu on 8/14/17.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CorsFilter extends OncePerRequestFilter {

    private static final Log LOG = LogFactory.getLog(CorsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOG.info("In CorsFilter...");

        // Add Cross Origin Header
        response.addHeader("Access-Control-Allow-Origin", "*");

        // Preflight Checks
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1");
        }

        filterChain.doFilter(request, response);
    }
}
