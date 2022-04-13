package com.company.panels;

import com.company.entities.Book;
import com.company.commonService.LoadingFileData;
import com.company.shelves.Shelves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.company.constants.Constants.BOOK_LOAD_PATH;
import static com.company.constants.Constants.CUSTOMER_PATH;
import static com.company.constants.Constants.DISCOUNT_PATH;


public class BookStoreImpl implements BookStoreService {

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

    public BookStoreImpl() {
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

}
