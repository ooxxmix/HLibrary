package com.ooxxmix.hlibrary.model.bean;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

public class Book {

    private final String regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
    private final Pattern pattern = Pattern.compile(regex);

    private String name;
    private String image;
    private String isbn;

    public Book (@NonNull String isbn) throws ISBNFormatError {
        setIsbn(isbn);
    }

    public Book (@NonNull String isbn, @NonNull String name) throws ISBNFormatError {
        this(isbn);
        setName(name);
    }

    public Book (@NonNull String isbn, @NonNull String name, String image) throws ISBNFormatError {
        this(name, isbn);
        setImage(image);
    }

    public String getIsbn () {
        return isbn;
    }

    public void setIsbn (@NonNull String isbn) throws ISBNFormatError {
        if (pattern.matcher(isbn).matches()) this.isbn = isbn;
        else throw new ISBNFormatError();
    }

    public String getName () {
        return name;
    }

    public void setName (@NonNull String name) {
        this.name = name;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public class ISBNFormatError extends Exception {

        public ISBNFormatError () {
            super("ISBN Format Error");
        }

    }
}
