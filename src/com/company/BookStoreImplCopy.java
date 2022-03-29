package com.company;

import com.company.commonService.LoadingFileData;
import com.company.constants.Constants;
import com.company.entities.Book;
import com.company.panels.Validation;
import com.company.panels.adminPanel.AdminService;
import com.company.shelves.Shelves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.company.constants.Constants.BOOK_LOAD_PATH;
import static com.company.constants.Constants.CUSTOMER_PATH;
import static com.company.constants.Constants.DISCOUNT_PATH;


public class BookStoreImplCopy implements AdminService {
    // Найти точки наследование в классах
    // Текующию дату добавление и сортировка по деталям // Created date // Created by // Edited by

    private LoadingFileData loadingFileData;
    protected List<Book> bookList;
    private List<String> purchaseList;
    private HashMap<Integer, Book> bookGenre;
    private HashMap<String , String> filePath;
    private List<Shelves> shelves;
    private int balance;
    protected Validation validation;
    protected boolean checkIdRange;
    protected boolean checkBookId;
    private HashMap<Shelves, Book> bookData = new HashMap<>();

    public BookStoreImplCopy() {
        try {
            loadingFileData = new LoadingFileData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        validation = new Validation();
        init();
    }
    public void init(){
        bookList = loadingFileData.getMainBookList();
        purchaseList = loadingFileData.getPurchaseList();
        bookGenre = loadingFileData.getBookGenre();
        filePath = loadingFileData.getFilePath();
        shelves = loadingFileData.getShelvesList();
        balance = loadingFileData.getBalance();
    }

    public void addToShelve(int shelfId, Book book){
        bookData.put(shelves.get(shelfId), book);
    }

    public boolean checkStatus(int id){
        checkIdRange = validation.checkIdRange(id, printAllBooks());
        checkBookId = validation.checkBookId(id, printAllBooks());
        if (checkIdRange && checkBookId)
            return true;

        return false;
    }


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

    public List<Book> searchBook(String bookName) {
        List<Book> results = new ArrayList<>();
        for(Book book : bookList){
            String [] nameSplit = book.getName().split(" ");
            for (String name: nameSplit) {
                if (name.equals(bookName)){
                    results.add(book);
                    break;
                }
            }
        }
        return results;
    }
    public List<Book> searchByGenre(String genre){
        List<Book> results = new ArrayList<>();
        for (Book book: bookList) {
            if (book.getGenre().equals(genre)){
                results.add(book);
            }
        }
        if (results.size() == 0){
            return null;
        } else{
            return results;
        }
    }

    public List<Book> printAllBooks() {
        if (bookList != null && bookList.size() !=0){
            return bookList;
        }
        return null;
    }

    public void saveBooks() throws IOException {
        File saveBook = new File(filePath.get(BOOK_LOAD_PATH));
        FileWriter fileWriter = new FileWriter(saveBook);

        for (Book book: bookList) {
            fileWriter.write(book.getId()+ ":" +
                    book.getName() + ":" +
                    book.getAuthor() + ":" +
                    book.getGenre() + ":" +
                    book.getPrice() + ":" +
                    book.getAmount() + ":"+
                    book.getAmountLeft() + ":"+
                    new SimpleDateFormat("dd/MM/yyyy").format(book.getDate()) +
                    "\n");
        }
        fileWriter.flush();
    }



    public void saleBook(int bookId, String customerName, List<Book> searchResult) throws IOException {
        if (validation.checkBookId(bookId, searchResult)){
                deductBookAmount(bookId, searchResult);
                processUserPurchase(bookId, customerName);
        }
    }

    public void processUserPurchase(int bookId, String userName) throws IOException {
        File file = new File(filePath.get(CUSTOMER_PATH));
        FileWriter fileWriter = new FileWriter(file);

        for (Book book : bookList){
            if (book.getId() == bookId){
                purchaseList.add(
                        book.getId() + ":" +
                                book.getName() + ":" +
                                book.getAuthor() + ":" +
                                book.getAmount() + ":" +
                                book.getAmountLeft() + ":" +
                                book.getPrice() +
                                " - " + userName
                );
                balance = book.getPrice();
                break;
            }
        }
        for (int i = 0; i < purchaseList.size(); i++) {
            fileWriter.write(purchaseList.get(i) + (purchaseList.size()-1 != i ? "\n" : ""));
        }
        loadingFileData.loadBalance();
        fileWriter.flush();
        fileWriter.close();
    }


    public void deductBookAmount(int bookId,  List<Book> chosenBooks) {
        for (Book book: chosenBooks) {
            if (book.getId() == bookId){
                book.setAmountLeft(book.getAmountLeft()-1);
                System.out.println(book.getAmountLeft());
                try {
                    saveBooks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public Boolean checkBookExist(int bookId,  List<Book> chosenBooks){
        for (Book book: chosenBooks) {
            if (book.getId() == bookId && book.getAmount() !=0){
                return true;
            }
        }
        return false;
    }

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

    public void addDiscount (){
        Scanner scanner = new Scanner(System.in);
        for (Book books: bookList) {
            System.out.println(books);
        }
        System.out.println("Please type in id of the book that you want to add a discount: ");
        String chooseId = scanner.nextLine();
        System.out.println("Please add a discount(in percent): ");
        int discount = scanner.nextInt();
        String discountString = String.valueOf(discount);
        try {
            FileWriter fileWriter = new FileWriter("src/com/company/DataBase/DiscountedBooks.txt");

            fileWriter.write(
            bookList.get(Integer.parseInt(chooseId)).getId() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getName() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAuthor() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getPrice() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAmount() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAmountLeft() + ":" +
                discountString
            );
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateDiscount() throws FileNotFoundException {
        File file = new File(filePath.get(DISCOUNT_PATH));

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String word = scanner.nextLine();
            String[] bookLine = word.split(":");
            int amount = bookList.get(Integer.parseInt(bookLine[0])).getPrice() / Integer.parseInt(bookLine[bookLine.length-1]);
            bookList.get(Integer.parseInt(bookLine[0])).setAmount(amount);
        }
    }

    public void createShelve(String shelveType){
        Shelves newShelve = new Shelves(shelves.size()+1,shelveType);
        shelves.add(newShelve);
        loadingFileData.setShelvesList(shelves);
        try {
            loadingFileData.reloadShelveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void blaBla() {

    }

}
