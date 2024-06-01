package com.hesabbook.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Message {
    private String sender;
    private String receiver;
    private String content;
    private Date time;
}
