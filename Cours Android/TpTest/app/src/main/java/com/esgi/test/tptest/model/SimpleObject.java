package com.esgi.test.tptest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by furki on 25/03/2016.
 */
public class SimpleObject  implements Serializable{
    int value;
    Date date;

    public SimpleObject(int value, Date date) {
        this.value = value;
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
