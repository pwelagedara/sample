package com.devopstools.portal.config;

import com.devopstools.portal.model.dto.ErrorDto;
import com.devopstools.portal.util.FilterUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomBasicAuthenticationEntrypoint extends BasicAuthenticationEntryPoint {

    private static final Log LOG = LogFactory.getLog(CustomBasicAuthenticationEntrypoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        LOG.info("Responding with a 401");

        // Respond with status 401
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ErrorDto error = new ErrorDto(httpStatus.value(), httpStatus.name(), authException.getMessage());
        response.getWriter().write(FilterUtils.convertObjectToJson(error));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Pubudu");
        super.afterPropertiesSet();
    }
}
