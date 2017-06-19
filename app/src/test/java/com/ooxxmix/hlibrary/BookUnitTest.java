package com.ooxxmix.hlibrary;

import com.ooxxmix.hlibrary.model.bean.Book;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BookUnitTest {

    @Test
    public void setValidISBNTest () {
        List<String> isbns = new ArrayList<String>();
        //Valid ISBNs
        isbns.add("ISBN 978-0-596-52068-7");
        isbns.add("ISBN-13: 978-0-596-52068-7");
        isbns.add("978 0 596 52068 7");
        isbns.add("9780596520687");
        isbns.add("0-596-52068-9");
        isbns.add("0 512 52068 9");
        isbns.add("ISBN-10 0-596-52068-9");
        isbns.add("ISBN-10: 0-596-52068-9");

        for (String isbn : isbns) {
            boolean isPass = true;
            try {
                Book b = new Book(isbn);
            } catch (Book.ISBNFormatError e) {
                isPass = false;
            }
            assertTrue(isPass);
        }
    }

    @Test
    public void setInvalidISBNTest () {
        List<String> isbns = new ArrayList<String>();

        //Invalid ISBNs
        isbns.add("ISBN 11978-0-596-52068-7");
        isbns.add("ISBN-12: 978-0-596-52068-7");
        isbns.add("978 10 596 52068 7");
        isbns.add("119780596520687");
        isbns.add("0-5961-52068-9");
        isbns.add("11 5122 52068 9");
        isbns.add("ISBN-11 0-596-52068-9");
        isbns.add("ISBN-10- 0-596-52068-9");

        for (String isbn : isbns) {
            boolean isPass = true;
            try {
                Book b = new Book(isbn);
            } catch (Book.ISBNFormatError e) {
                isPass = false;
            }
            assertFalse(isPass);
        }
    }
}
