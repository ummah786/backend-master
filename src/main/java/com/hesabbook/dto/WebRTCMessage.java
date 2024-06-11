package com.hesabbook.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WebRTCMessage {
    private String from;
    private String to;
    private String sdp;
    private String type;
    private String candidate;
}
