package com.library.ui;
import com.library.model.Book;
import com.library.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Map<String, List<Book>> issueBooks = new HashMap<>();

        boolean flag = true;
        while(flag){
            System.out.println("======Welcome to Library Management System======");
            System.out.println("1. Add Book");
            System.out.println("2. Register a New User");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display Books Issued to a User");
            System.out.println("7. Display Available Books sorted by title and author");
            System.out.println("8. Quit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Book issued id:");
                    String book_issued_id = sc.nextLine();
                    // Check for duplicates
                    boolean duplicateBook = false;
                    for(Book b: books){
                        if(b.getBook_id().equals(book_issued_id)){
                            duplicateBook = true;
                            break;
                        }
                    }
                    if (duplicateBook) {
                        System.out.println("A book with this ID already exists.");
                        break;
                    }
                    // ------
                    System.out.println("Enter Book name:");
                    String book_name = sc.nextLine();
                    System.out.println("Enter Book author:");
                    String book_author = sc.nextLine();
                    Book book = new Book(book_issued_id, book_name, book_author);
                    books.add(book);
                    book.addedBooks();
                    break;

                case 2:
                    System.out.println("Enter User id:");
                    String user_id = sc.nextLine();
                    // Check for duplicates
                    boolean duplicateUser = false;
                    for(User u: users){
                        if(u.getUser_id().equals(user_id)){
                            duplicateUser = true;
                            break;
                        }
                    }
                    if (duplicateUser) {
                        System.out.println("A User with this ID already exists.");
                        break;
                    }
                    // ------
                    System.out.println("Enter User name:");
                    String user_name = sc.nextLine();
                    System.out.println("Enter User email:");
                    String user_email = sc.nextLine();
                    User user = new User(user_id, user_name, user_email);
                    users.add(user);
                    user.registeredUser();
                    break;

                case 3:
                    //Check if user exists
                    System.out.println("Enter User id:");
                    String issue_user_id = sc.nextLine();
                    User foundUser = null;
                    for(User u: users){
                        if(u.getUser_id().equals(issue_user_id)){
                            foundUser = u;
                        }
                    }
                    if (foundUser == null) {
                        System.out.println("User not found. Please register first.");
                        break;
                    }
                    //check if book exists
                    System.out.println("Enter Book id:");
                    String issue_book_id = sc.nextLine();
                    Book foundBook = null;
                    for(Book b: books){
                        if(b.getBook_id().equals(issue_book_id)){
                            foundBook = b;
                        }
                    }
                    if (foundBook == null) {
                        System.out.println("Book not found.");
                        break;
                    }
                    if (foundBook.isIssued()) {
                        System.out.println("This book is already issued.");
                        break;
                    }
                    // Issue the book
                    foundBook.setIssued(true);
                    issueBooks.putIfAbsent(issue_user_id, new ArrayList<>());
                    issueBooks.get(issue_user_id).add(foundBook);

                    System.out.println("Book '" + foundBook.getBook_name() + "' issued to " + foundUser.getUser_name());
                    break;

                case 4:
                    System.out.println("Enter User id:");
                    String return_user_id = sc.nextLine();
                    User foundReturnUser = null;
                    for(User u: users){
                        if(u.getUser_id().equals(return_user_id)){
                            foundReturnUser = u;
                            break;
                        }
                    }
                    if (foundReturnUser == null) {
                        System.out.println("User not found.");
                        break;
                    }
                    System.out.println("Enter Book id:");
                    String return_book_id = sc.nextLine();
                    Book foundReturnBook = null;
                    for(Book b: books){
                        if(b.getBook_id().equals(return_book_id)){
                            foundReturnBook = b;
                            break;
                        }
                    }
                    if (foundReturnBook == null) {
                        System.out.println("Book not found. Enter valid book id.");
                        break;
                    }
                    if(!issueBooks.containsKey(return_user_id)){
                        System.out.println("No book issued to " + foundReturnUser.getUser_name());
                        break;
                    }
                    List<Book> userBooks = issueBooks.get(return_user_id);
                    Book bookToReturn = null;
                    for(Book b: userBooks){
                        if(b.getBook_id().equals(return_book_id)){
                            bookToReturn = b;
                            break;
                        }
                    }
                    if (bookToReturn == null) {
                        System.out.println("The book was not issued to this user.");
                        break;
                    }
                    // Return book
                    bookToReturn.setIssued(false);
                    userBooks.remove(bookToReturn);
                    System.out.println("Book '" + bookToReturn.getBook_name() + "' returned successfully by " + foundReturnUser.getUser_name());
                    break;

                case 5:
                    if (books.isEmpty()) {
                        System.out.println("No books available in the library.");
                        break;
                    }
                    System.out.println("===== All Books in the Library =====");
                    for (Book b : books) {
                        System.out.println("Book ID: " + b.getBook_id());
                        System.out.println("Title  : " + b.getBook_name());
                        System.out.println("Author : " + b.getBook_author());
                        System.out.println("Status : " + (b.isIssued() ? "Issued" : "Available"));
                        System.out.println("----------------------------------");
                    }
                    break;

                case 6:
                    System.out.println("Enter User id:");
                    String books_each_user = sc.nextLine();
                    User eachUser = null;
                    for(User u: users){
                        if(u.getUser_id().equals(books_each_user)){
                            eachUser = u;
                        }
                    }
                    if (eachUser == null) {
                        System.out.println("User not found. Please register first.");
                        break;
                    }
                    // Checking if user has issued any books
                    List<Book> bookEachUser = issueBooks.get(books_each_user);
                    if(bookEachUser == null || bookEachUser.isEmpty()){
                        System.out.println("User has not issued yet.");
                        break;
                    }
                    // Display issued books
                    System.out.println("Book issued by " + eachUser.getUser_name());
                    for(Book b: bookEachUser){
                        System.out.println("-"+ b.getBook_name()+" by "+b.getBook_author());
                    }
                    break;

                case 7:
                    List<Book> availableBooks = new ArrayList<>();
                    for (Book b : books) {
                        if (!b.isIssued()) {
                            availableBooks.add(b);
                        }
                    }
                    if (availableBooks.isEmpty()) {
                        System.out.println("No available books in the library.");
                        break;
                    }
                    // Sort by title, then by author
                    availableBooks.sort((b1, b2) -> {
                        int titleCompare = b1.getBook_name().compareToIgnoreCase(b2.getBook_name());
                        if (titleCompare != 0) {
                            return titleCompare;
                        }
                        return b1.getBook_author().compareToIgnoreCase(b2.getBook_author());
                    });
                    // Display sorted books
                    System.out.println("===== Available Books (Sorted) =====");
                    for (Book b : availableBooks) {
                        System.out.println("Book ID: " + b.getBook_id());
                        System.out.println("Title  : " + b.getBook_name());
                        System.out.println("Author : " + b.getBook_author());
                        System.out.println("----------------------------------");
                    }
                    break;

                case 8:
                    flag = false;
                    System.out.println("Quit");
                    break;

                default:
                    System.out.println("Invalid choice. Enter between 1 and 8");
                    break;
            }
        }
    }
}