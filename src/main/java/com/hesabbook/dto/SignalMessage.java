package com.hesabbook.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SignalMessage {
    private String type;
    private String data;
    private String from;
    private String to;
    private String signal;
}
