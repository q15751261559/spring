package cn.edu.niit.javabean;

import java.util.Date;

public class Book {
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", sort='" + sort + '\'' +
                ", description='" + description + '\'' +
                ", store=" + store +
                ", borrow=" + borrow +
                ", borrow_date=" + borrow_date +
                ", return_date=" + return_date +
                '}';
    }

    private int id;
    private String name;
    private String author;
    private String sort;
    private String guihuan;

    public String getGuihuan() {
        return guihuan;
    }

    public void setGuihuan(String guihuan) {
        this.guihuan = guihuan;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Book(String name, String author, String sort, Date borrow_date, Date return_date) {
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    private String description;
    private boolean store;
    private boolean borrow;
    private Date borrow_date;
    private Date return_date;

    public Book(int id, String name, String author, String sort, String description, Date borrow_date, Date return_date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public Book(int id, String name, String author, String sort, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
    }

    public Book(int id, String name, String author, String sort,
                String description,boolean store) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
        this.store=store;
    }
}
