package cn.edu.niit.javabean;

import java.util.Date;

public class Message {
    private String reader;

    public Message() {

    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message(String reader, String detail, Date date) {
        this.reader = reader;
        this.detail = detail;
        this.date = date;
    }

    private String detail;
    private Date date;
}
