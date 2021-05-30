package cn.edu.niit.javabean;

public class User {
    private int card_id;
    private String username;
    private String password;
    private String reader;
    private String xingbie;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public User(int card_id, String username, String password, String reader, String describe, String email) {
        this.card_id = card_id;
        this.username = username;
        this.password = password;
        this.reader = reader;
        this.describe = describe;
        this.email = email;
    }

    private String describe;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(int card_id, String username, String password, String reader, String describe, String email, int sex, String phone) {
        this.card_id = card_id;
        this.username = username;
        this.password = password;
        this.reader = reader;
        this.describe = describe;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
    }

    private int sex;
    private String phone;

    public User(int card_id, String username, String password, String reader, String describe, String email, int sex) {
        this.card_id = card_id;
        this.username = username;
        this.password = password;
        this.reader = reader;
        this.describe = describe;
        this.email = email;
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int card_id, String username, String password, String reader, String describe) {
        this.card_id = card_id;
        this.username = username;
        this.password = password;
        this.reader = reader;
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

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
