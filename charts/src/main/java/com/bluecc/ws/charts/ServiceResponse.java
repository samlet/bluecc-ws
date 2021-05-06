package com.bluecc.ws.charts;

import java.util.List;
import java.util.Map;

public class ServiceResponse {
    // "statusCode": 200,
    //  "statusDescription": "OK",
    //  "data": {}
    private int statusCode;
    private String statusDescription;
    private Map<String,Object> data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<Object> getResultList(String itemName){
        return (List<Object>) this.getData().get(itemName);
    }
}
