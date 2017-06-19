package com.ooxxmix.hlibrary;

import com.ooxxmix.hlibrary.model.BooksSearcher;
import com.ooxxmix.hlibrary.model.bean.Book;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SearcherUnitTest {

    @Test
    public void searchViaValidISBN () {
        boolean isPass = true;
        BooksSearcher bs = new BooksSearcher();
        try {
            Book b = bs.search("9780596520687");
        } catch (Book.ISBNFormatError isbnFormatError) {
            isbnFormatError.printStackTrace();
            isPass = false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            isPass = false;
        }
        assertTrue(isPass);
    }

    @Test
    public void searchViaInvalidISBN () {
        boolean isPass = true;
        BooksSearcher bs = new BooksSearcher();
        try {
            Book b = bs.search("9780596520688");
        } catch (Book.ISBNFormatError isbnFormatError) {
            isbnFormatError.printStackTrace();
            isPass = true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            isPass = false;
        }
        assertFalse(isPass);
    }

}
