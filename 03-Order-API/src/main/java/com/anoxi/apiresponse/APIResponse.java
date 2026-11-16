package com.anoxi.apiresponse;

import lombok.Data;

@Data
public class APIResponse<T> {

    private Integer status;
    private String msg;
    private T data;
}
