package com.library.model;

public class Book {
    private String book_id;
    private String book_name;
    private String book_author;
    private boolean isIssued;

    public Book(String book_id, String book_name, String book_author) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.isIssued = false;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public void addedBooks() {
        System.out.println("Book Added: " + book_name + " by " + book_author);
    }
}
