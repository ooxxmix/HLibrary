package com.ooxxmix.hlibrary.model;

import android.support.annotation.NonNull;

import com.ooxxmix.hlibrary.model.bean.Book;

import java.io.IOException;
import java.net.MalformedURLException;

interface Searcher {

    Book search (@NonNull String isbn) throws Book.ISBNFormatError, IOException;

    String getSourceName ();

}
