package com.company.panels.userPanel;

import com.company.entities.Book;
import com.company.panels.BookStoreService;

import java.io.IOException;
import java.util.List;

public interface UserService extends BookStoreService {

    public void buyBook();

    String[] userProfile(String userId);

    void changeCredentials(int oldValue, String newValue);

    List<Book> printAllBooks();

    void saleBook(int bookId, String customerName, List<Book> searchResult) throws IOException;
}
