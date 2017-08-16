package com.example.francisco.w3d1weekday;

/**
 * Created by FRANCISCO on 08/08/2017.
 */

public class MessageEvent {
    String action,message;

    public MessageEvent(String action, String message) {
        this.action = action;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
