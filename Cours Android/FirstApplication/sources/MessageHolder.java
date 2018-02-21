package com.damie.firstapplication;

import java.io.Serializable;

/**
 * Created by damie on 04/10/2017.
 */

public class MessageHolder implements Serializable{

    String message;

    @Override
    public String toString() {
        return "com.damie.firstapplication.MessageHolder{" +
                "message='" + message + '\'' +
                '}';
    }

    public MessageHolder(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
