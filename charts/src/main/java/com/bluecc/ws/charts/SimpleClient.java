package com.bluecc.ws.charts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

import static com.bluecc.ws.common.Backend.getUnsafeOkHttpClient;

public class SimpleClient {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        System.out.println(".. run");
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = getUnsafeOkHttpClient();
        Map<String, String> map = ImmutableMap.of("entityName", "Example");
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(map));
        Request request = new Request.Builder()
                .url("https://localhost:8443/rest/services/findCc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTG9naW5JZCI6ImFkbWluIiwiaXNzIjoiQXBhY2hlT0ZCaXoiLCJleHAiOjE2MzQ1OTc3NTcsImlhdCI6MTYxNjU5Nzc1N30.Luuf2bK7ZJ8KE_CtsA3iPZ189i-Qbm2qK5r5VfeQcJqIyTKy4DHf2fBAp37W8OtU6SIplwCdnbTMtHuCZ5h8cA")
                .build();


        ResponseBody responseBody = client.newCall(request).execute().body();
        ServiceResponse entity = objectMapper.readValue(responseBody.string(), ServiceResponse.class);
        System.out.println(entity.getStatusCode());
        System.out.println("result data: ");
        for(Object o:entity.getResultList("result")){
            System.out.println(o.toString());
        }
    }
}

