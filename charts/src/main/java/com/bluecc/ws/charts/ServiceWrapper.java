package com.bluecc.ws.charts;

import com.google.common.collect.ImmutableMap;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.Header;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.ProducesJson;
import com.linecorp.armeria.server.annotation.RequestObject;

import java.util.concurrent.CompletableFuture;

public class ServiceWrapper {
    public static void serve(String name, int port, Object action){
        ServerBuilder sb = Server.builder();
        sb.http(port);
        sb.service("/", (ctx, req) -> HttpResponse.of(name));
        sb.annotatedService(action);

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
    }
}

