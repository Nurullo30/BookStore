package com.company;

import java.io.IOException;
import java.util.List;

public interface BookStoreInterface {


    Book addBook(String name, String author, String genre, int price, int amount);

    Book changeDetails(String authorOrName, int bookId, String newChange) throws IOException;

    void removeBook(int id);

    List<Book> searchBook(String bookName);

    boolean checkStatus(int id);

    List<Book> searchByGenre(String genre);

    List<Book> printAllBooks();

    void saleBook(int bookId, String customerName, List<Book> searchResult) throws IOException;

    void deductBookAmount(int bookId,  List<Book> chosenBooks);

    void printMostSold();

    void addDiscount ();

    void createShelve(String shelveType);
}
