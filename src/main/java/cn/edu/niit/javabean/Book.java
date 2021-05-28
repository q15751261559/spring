package cn.edu.niit.javabean;

public class Book {
    private int id;
    private String name;
    private String author;
    private String sort;
    private String description;
    private boolean store;

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
