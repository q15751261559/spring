package cn.edu.niit.javabean;

public class User {
    private int card_id;
    private String username;
    private String password;
    private String reader;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public User() {
    }


    public User(int card_id,String username, String password, String reader) {
        this.card_id=card_id;
        this.username = username;
        this.password = password;
        this.reader = reader;
    }
}
