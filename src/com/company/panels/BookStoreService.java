package com.company.panels;

import com.company.entities.Book;

import java.util.List;

public interface BookStoreService {

    List<Book> searchBook(String bookName);

    List<Book> searchByGenre(String genre);

    List<Book> printAllBooks();
}
