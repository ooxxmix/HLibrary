package com.ooxxmix.hlibrary.model;

import android.support.annotation.NonNull;

import com.ooxxmix.hlibrary.model.bean.Book;

interface Searcher {

    Book search (@NonNull String isbn);

    String getSourceName ();

}
