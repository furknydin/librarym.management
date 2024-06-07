package com.frknydin.librarym.management.config.interceptor;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static com.frknydin.librarym.management.config.interceptor.InterceptorConstants.*;

public class RequestSetHeaderInterceptor implements ClientHttpRequestInterceptor {
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add(X_TRACE_ID, MDC.get(X_TRACE_ID));
        return execution.execute(request, body);
    }
}
