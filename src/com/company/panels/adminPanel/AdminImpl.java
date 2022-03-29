package com.company.panels.adminPanel;
import com.company.entities.Book;
import com.company.panels.BookStoreImpl;
import com.company.constants.Constants;

import java.io.IOException;
import java.util.Date;

public class AdminImpl extends BookStoreImpl implements AdminService {

    public AdminImpl(){
    }

    @Override
    public Book addBook(String name, String author, String genre, int  price , int amount){

        bookList.add(new Book(bookList.size()+1, name, author, genre, price, amount, amount, new Date()));
        try {
            saveBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Book book =  bookList.get(bookList.size()-1);
        return book;
    }

    @Override
    public boolean checkStatus(int id){
        checkIdRange = validation.checkIdRange(id, printAllBooks());
        checkBookId = validation.checkBookId(id, printAllBooks());
        if (checkIdRange && checkBookId)
            return true;

        return false;
    }

    @Override
    public Book changeDetails(String authorOrName , int bookId ,String newChange) throws IOException {
        for (Book book : bookList) {
            if (book.getId() == bookId){
                switch (authorOrName) {
                    case Constants.AUTHOR:
                        book.setAuthor(newChange);
                        saveBooks();
                        return book;
                    case Constants.NAME:
                        book.setName(newChange);
                        saveBooks();
                        return book;
                }
            }
        }
        return null;
    }

    @Override
    public void removeBook(int id) {
        checkIdRange = validation.checkIdRange(id , bookList);
        boolean checkBookId = validation.checkBookId(id , bookList);
        if (checkIdRange && checkBookId){
            for (Book book: bookList) {
                if (book.getId() == id){
                    bookList.remove(book);
                    System.out.println("Removed: \n" + book);
                    break;
                }
            }
            try {
                saveBooks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Извините нет у нас книги с такой ID");
        }
    }

    @Override
    public void printMostSold(){
        int maxAmount = bookList.get(0).getAmount()-bookList.get(0).getAmountLeft();

        for (int i = 0; i < bookList.size()-1; i++) {
            System.out.println(bookList.get(i) + " was sold : " + " " + (bookList.get(i).getAmount()-bookList.get(i).getAmountLeft()));

            int amountSold = bookList.get(i+1).getAmount()-bookList.get(i+1).getAmountLeft();
            if (maxAmount < amountSold){
                maxAmount = amountSold;
            }
        }
        System.out.println(bookList.get(bookList.size()-1) + " was sold : " + " " +
                (bookList.get(bookList.size()-1).getAmount()-bookList.get(bookList.size()-1).getAmountLeft()) + "\n");

        if (maxAmount == 0){
            System.out.println("Anything has not been sold yet");
        } else {
            // checking in case if 2 books was sold at the same amount
            for (Book book: bookList) {
                if (maxAmount == (book.getAmount()-book.getAmountLeft())){
                    System.out.println( book + "is the most sold book for now");
                }
            }
        }
    }


















    @Override
    public void blaBla() {

    }

}
