package com.frknydin.librarym.management.config.interceptor;

import com.frknydin.librarym.management.config.interceptor.wrapper.RequestWrapper;
import com.frknydin.librarym.management.config.interceptor.wrapper.ResponseWrapper;

import com.frknydin.librarym.management.service.TraceIdGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.io.IOUtils;


import java.io.IOException;

import static com.frknydin.librarym.management.config.interceptor.InterceptorConstants.X_TRACE_ID;

@Component
public class LoggingInterceptorConfig extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptorConfig.class);

    private final TraceIdGenerator traceIdGenerator;

    public LoggingInterceptorConfig(TraceIdGenerator traceIdGenerator) {
        this.traceIdGenerator = traceIdGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        traceIdGenerator.generateRandomTraceId();
        setResponseHeaders(responseWrapper);
        logRequest(requestWrapper);
        filterChain.doFilter(requestWrapper,responseWrapper);
        logResponse(responseWrapper);
    }

    private void setResponseHeaders(HttpServletResponse response){
        response.addHeader(X_TRACE_ID, MDC.get(X_TRACE_ID));
    }

    private void logResponse(ResponseWrapper responseWrapper){
        String log = String.format("Response Status: %s, Response Headers: %s, Response Body: %s",
                responseWrapper.getStatus(),
                responseWrapper.getAllHeaders(),
                IOUtils.toString(responseWrapper.getBody(),responseWrapper.getCharacterEncoding()));

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(log);
        }
    }

    private void logRequest(RequestWrapper requestWrapper) {
        String log = String.format("## %s ## Request Method: %s, RequestUrl: %s, RequestHeaders: %s, RequestBody: %s",
                requestWrapper.getServerName(),
                requestWrapper.getMethod(),
                requestWrapper.getRequestURL(),
                requestWrapper.getAllHeaders(),
                RequestWrapper.body);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(log);
        }
    }
}
