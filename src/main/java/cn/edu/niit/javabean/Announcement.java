package cn.edu.niit.javabean;

import java.util.Date;

public class Announcement {
    private String title;
    private String detail;
    private Date date;

    public Announcement() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Announcement(String title, String detail, Date date) {
        this.title = title;
        this.detail = detail;
        this.date = date;
    }
}
