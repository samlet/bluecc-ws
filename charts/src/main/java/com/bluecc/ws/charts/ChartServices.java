package com.bluecc.ws.charts;

import com.bluecc.ws.charts.stuffs.MessageConverterService;
import com.linecorp.armeria.common.*;
import com.linecorp.armeria.server.AbstractHttpService;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.*;
import com.linecorp.armeria.server.logging.LoggingService;

import java.util.concurrent.CompletableFuture;

public class ChartServices {
    public static void main(String[] args) {
        ServerBuilder sb = Server.builder();
        sb.http(1080);

        // Add a simple 'Hello, world!' service.
        sb.service("/", (ctx, req) -> HttpResponse.of("ok!"));

        // Using path variables:
        sb.service("/greet/{name}", new AbstractHttpService() {
            @Override
            protected HttpResponse doGet(ServiceRequestContext ctx, HttpRequest req) {
                String name = ctx.pathParam("name");
                return HttpResponse.of("hi, %s!", name);
            }
        }.decorate(LoggingService.newDecorator())); // Enable logging

        sb.annotatedService("/sys", new SysInfo());

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
    }
}
