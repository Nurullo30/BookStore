package com.company.panels.userPanel;

import com.company.entities.Book;

import java.util.List;

public interface UserService {

    public void buyBook();

    String[] userProfile(String userId);

    void changeCredentials(int oldValue, String newValue);

    List<Book> printAllBooks();
}
