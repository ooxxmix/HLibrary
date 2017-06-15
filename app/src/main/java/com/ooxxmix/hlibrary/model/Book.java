package com.ooxxmix.hlibrary.model;

public class Book {

    private String name;
    private String image;
    private String isbn;

    public Book (String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public Book (String name, String image, String isbn) {
        this(name, isbn);
        this.image = image;
    }

    public String getName () {
        return name;
    }

    public String getImage () {
        return image;
    }

    public String getIsbn () {
        return isbn;
    }
}
