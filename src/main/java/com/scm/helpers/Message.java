package com.scm.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;

}