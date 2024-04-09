package com.hesabbook.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ChatMessage {
    private String content;
    private String sender;
    private String receiver;
}
