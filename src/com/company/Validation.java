package com.company;

import java.io.IOException;
import java.util.List;

public class Validation {

    public Boolean checkBookId(int bookId, List<Book> bookList){
        for (Book book: bookList) {
            if (book.getId() == bookId && book.getAmount() !=0){
                return true;
            }
        }
        return false;
    }

    public Boolean checkIdRange(int bookId, List<Book> bookList){
        if (bookId <= bookList.size() && bookId >= 1){return true;}
        return false;
    }







}
