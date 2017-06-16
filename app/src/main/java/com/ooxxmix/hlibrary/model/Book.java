package com.ooxxmix.hlibrary.model;

public class Book {

    private String name;
    private String image;
    private String isbn;

    public Book (String isbn) {
        this.isbn = isbn;
    }

    public Book (String name, String isbn) {
        this(isbn);
        this.name = name;
    }

    public Book (String name, String image, String isbn) {
        this(name, isbn);
        this.image = image;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public String getIsbn () {
        return isbn;
    }

    public void setIsbn (String isbn) {
        this.isbn = isbn;
    }
}
