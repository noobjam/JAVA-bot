package com.example.javabot;

import javafx.scene.image.Image;

public class Message {
    private String content;
    private Image senderImage;
    private boolean incoming;

    public Message(String content, Image senderImage, boolean incoming) {
        this.content = content;
        this.senderImage = senderImage;
        this.incoming = incoming;
    }

    public String getContent() {
        return content;
    }

    public Image getSenderImage() {
        return senderImage;
    }

    public boolean isIncoming() {
        return incoming;
    }
}

