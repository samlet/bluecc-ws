package com.bluecc.ws.charts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.linecorp.armeria.common.logging.LogLevel;
import com.linecorp.armeria.server.annotation.Header;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.ProducesJson;
import com.linecorp.armeria.server.annotation.RequestObject;
import com.linecorp.armeria.server.annotation.decorator.LoggingDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LoggingDecorator(
        requestLogLevel = LogLevel.INFO,            // Log every request sent to this service at INFO level.
        successfulResponseLogLevel = LogLevel.INFO  // Log every response sent from this service at INFO level.
)
public class SysInfo {
    private static final Logger logger = LoggerFactory.getLogger(SysInfo.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Post("/timestamp")
    @ProducesJson
    public GenericResponse timestamp(@Header String xWorkload,
                                     @Header String xToken,
                                     @RequestObject RequestInfo input) {
        final String caller = input.getCaller();
        logger.info("caller is {}, workload: {}, token: {}", caller, xWorkload, xToken);
        return new GenericResponse(GenericResponse.SUCCESS, caller,
                ImmutableMap.of("ts", System.currentTimeMillis()));
    }

}
