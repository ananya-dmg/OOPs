package com.library.model;

public class User {
    private String user_id;
    private String user_name;
    private String user_email;
//    public String book_issued_id;

    public User(String user_id, String user_name, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

//    public String getBook_issued_id() {
//        return book_issued_id;
//    }
//
//    public void setBook_issued_id(String book_issued_id) {
//        this.book_issued_id = book_issued_id;
//    }

    public void registeredUser() {
        System.out.println(this.user_name + " Registered Successfully");
    }
}
