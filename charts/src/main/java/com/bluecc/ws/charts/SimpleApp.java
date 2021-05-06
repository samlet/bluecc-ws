package com.bluecc.ws.charts;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

import static com.bluecc.ws.common.Backend.getUnsafeOkHttpClient;

public class SimpleApp {

    public static void main(String[] args) throws IOException {
        System.out.println(".. run");

        OkHttpClient client = getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"entityName\":\"Example\"\n}\n");
        Request request = new Request.Builder()
                .url("https://localhost:8443/rest/services/findCc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTG9naW5JZCI6ImFkbWluIiwiaXNzIjoiQXBhY2hlT0ZCaXoiLCJleHAiOjE2MzQ1OTc3NTcsImlhdCI6MTYxNjU5Nzc1N30.Luuf2bK7ZJ8KE_CtsA3iPZ189i-Qbm2qK5r5VfeQcJqIyTKy4DHf2fBAp37W8OtU6SIplwCdnbTMtHuCZ5h8cA")
                .build();

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = client.newCall(request).execute().body();
        Map<String,Object> entity = objectMapper.readValue(responseBody.string(), Map.class);
        System.out.println("result code "+entity.get("statusCode"));
    }
}

