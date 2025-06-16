package com.aurora.observability.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class ObservabilityLogger {
    private static final Logger logger = LoggerFactory.getLogger("ObservabilityLogger");

    public static void log(String resource, String action) {
        MDC.put("resource", resource);
        MDC.put("action", action);
        logger.info("Business event triggered");
        MDC.remove("resource");
        MDC.remove("action");
    }

}
