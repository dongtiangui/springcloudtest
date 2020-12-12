package com.hik.log.logmodule.domain;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -8604036493420749066L;
    private String row;
    private String timestamp;
    private String name1;
    private String age1;

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }

    @Override
    public String toString() {
        return "User{" +
                "row='" + row + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", name1='" + name1 + '\'' +
                ", age1='" + age1 + '\'' +
                '}';
    }
}
