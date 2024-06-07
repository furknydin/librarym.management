package com.frknydin.librarym.management.service;

import com.frknydin.librarym.management.config.interceptor.InterceptorConstants;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TraceIdGenerator {

    public String generateRandomTraceId() {
        String traceId = UUID.randomUUID().toString();
        MDC.put(InterceptorConstants.X_TRACE_ID, traceId);
        return traceId;
    }

}
