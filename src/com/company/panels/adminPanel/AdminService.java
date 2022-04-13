package com.company.panels.adminPanel;

import com.company.entities.Book;
import com.company.panels.BookStoreService;

import java.io.IOException;
import java.util.List;

public interface AdminService extends BookStoreService {

    Book addBook(String name, String author, String genre, int price, int amount);

    Book changeDetails(String authorOrName, int bookId, String newChange) throws IOException;

    void removeBook(int id);

    boolean checkStatus(int id);

    void deductBookAmount(int bookId,  List<Book> chosenBooks);

    void printMostSold();

    void addDiscount ();

    void createShelve(String shelveType);

    void blaBla();
}
