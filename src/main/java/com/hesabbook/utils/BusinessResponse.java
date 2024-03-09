package com.hesabbook.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BusinessResponse implements Serializable {
    private Integer code;
    private String status;
    private Object response;
    private Exception exception;
}
