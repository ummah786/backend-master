package com.hesabbook.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Message {
    private String content;
    private String sender;
}
