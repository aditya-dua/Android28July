package com.adityadua.dbsqlexample.model;

/**
 * Created by AdityaDua on 29/08/17.
 */

public class BookData {
// Book X : Book id = 10 id =1
// Book X : Book id = 10 id =2
    private String bookName;
    private String bookAuthor;
    private String id;
    private String bookId;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
