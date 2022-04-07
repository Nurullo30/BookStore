package com.company.panels.adminPanel;

import com.company.constants.CommonConstants;
import com.company.constants.UserConstants;
import com.company.entities.Book;
import com.company.constants.Constants;
import com.company.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private Book book;
    private AdminService adminImpl;
    private List<Book> bookList;
    private boolean exitMenu;
    public AdminMenu() {

    }

    public void init(User user){
        adminImpl = new AdminImpl();
    }

    public void startMenu(){
        while (true){
            System.out.println(CommonConstants.WELCOME_ADMIN);
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + CommonConstants.ONE + " "  + UserConstants.ADD_BOOK); // Цыфры с переди
            System.out.println(CommonConstants.TWO + " " + UserConstants.DELETE_BOOK);
            System.out.println(CommonConstants.THREE + " " + UserConstants.CHANGE_BOOK);
            System.out.println(CommonConstants.FOUR + " " + UserConstants.SEARCH);
            System.out.println(CommonConstants.FIVE + " " + UserConstants.SEARCH_BY_GENRE);
            System.out.println(CommonConstants.SIX + " " + UserConstants.SEE_ALL_BOOKS);
            System.out.println(CommonConstants.SEVEN + " " + UserConstants.MOST_SOLD);
            System.out.println(CommonConstants.EIGHT + " " + UserConstants.LEAST_SOLD);
            System.out.println(CommonConstants.NINE + " " + UserConstants.ADD_NEW_SHELF  + "\n");
            System.out.println(CommonConstants.CHOOSE_FUNCTION);
            boolean check = true;
            while (check){
                try{
                    int menuNumber = scanner.nextInt();
                    switch (menuNumber){
                        case 1:
                            System.out.println(CommonConstants.ONE + " "  + UserConstants.ADD_BOOK);
                            addBook();
                            check = false;
                            break;
                        case 2:
                            System.out.println(CommonConstants.TWO + " " + UserConstants.DELETE_BOOK);
                            removeBook();
                            check = false;
                            break;
                        case 3:
                            System.out.println(CommonConstants.THREE + " " + UserConstants.CHANGE_BOOK);
                            changeDetails();
                            check = false;
                            break;
                        case 4:
                            System.out.println(CommonConstants.FOUR + " " + UserConstants.SEARCH);
                            searchBook();
                            check = false;
                            break;
                        case 5:
                            System.out.println(CommonConstants.FIVE + " " + UserConstants.SEARCH_BY_GENRE);
                            searchByGenre();
                            check = false;
                            break;
                        case 6:
                            System.out.println(CommonConstants.SIX + " " + UserConstants.SEE_ALL_BOOKS);
                            printAllBooks();
                            check = false;
                            break;
                        case 7:
                            System.out.println(CommonConstants.SEVEN + " " + UserConstants.MOST_SOLD);
                            printMostSold();
                            check = false;
                            break;
                        default:
                            System.out.println(CommonConstants.TRY_AGAIN);
                    }
                }catch (Exception e){
                    scanner.nextLine();
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        boolean checkAddMenu = true;
        while (checkAddMenu){

            System.out.println(UserConstants.BOOK_NAME + ": " + CommonConstants.MAIN_MENU);
            String name = scanner.nextLine();
            if (name.equals(CommonConstants.STAR))
                break;

            System.out.println(UserConstants.BOOK_AUTHOR + ": " + CommonConstants.MAIN_MENU);
            String author = scanner.nextLine();
            if (author.equals(CommonConstants.STAR))
                break;

            System.out.println(UserConstants.BOOK_GENRE + ": " + CommonConstants.MAIN_MENU);
            String genre = scanner.nextLine();
            if (genre.equals(CommonConstants.STAR))
                break;

            System.out.println(UserConstants.BOOK_PRICE + ": " + CommonConstants.MAIN_MENU);
            String price = "";
            while (true){
                price = scanner.nextLine();
                if(numCheck(price)){
                    break;
                } else {
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
            if (price.equals(CommonConstants.STAR))
                break;

            System.out.println(UserConstants.BOOK_AMOUNT + ": " + CommonConstants.MAIN_MENU);
            String amount;

            while (true){
                amount = scanner.nextLine();
                String amountValue = "numCheck(price)";
                if(amountValue!= null){
                    amount = amountValue;
                    break;
                } else{
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
            if (amount.equals(CommonConstants.STAR))
                break;

            book = adminImpl.addBook(name, author, genre, Integer.parseInt(price), Integer.parseInt(amount));

            System.out.println(book != null ? (UserConstants.SUCCESSFULLY_ADDED + "\n" + book +"\n")
                    : (UserConstants.FAILED_ADDING + " \n"));

            exitMenu = true;
            while (exitMenu){
               System.out.println(CommonConstants.ONE + " " + UserConstants.ADD_BOOK + "/" + CommonConstants.MAIN_MENU);
               switch (scanner.nextLine()){
                   case CommonConstants.STAR:
                       checkAddMenu = false;
                       exitMenu = false;
                       break;
                   case CommonConstants.ONE:
                       exitMenu = false;
                       break;
                   default:
                       System.out.println(CommonConstants.TRY_AGAIN);
               }
           }
        }
    }

    public boolean numCheck(String userValue){

        String [] numbers = {CommonConstants.ZERO ,CommonConstants.ONE, CommonConstants.TWO, CommonConstants.THREE, CommonConstants.FOUR, CommonConstants.FIVE,
            CommonConstants.SIX,CommonConstants.SEVEN , CommonConstants.EIGHT, CommonConstants.NINE,};
        for (int i = 0; i < userValue.length(); i++) {
            for (int j = 0; j < numbers.length; j++) {
                String letter = "" + userValue.charAt(i);
                if (!letter.equals(numbers[j])){
                    return false;
                }
            }
        }
        return true;
    }

    public void printAllBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of our all books: ");
        bookList = adminImpl.printAllBooks();
        for (Book books : bookList) {
            System.out.println(books);
        }
        exitMenu = true;
        while (exitMenu){
            System.out.println("\n" + "Главное меню *");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }

    public void removeBook() {
        bookList = adminImpl.printAllBooks();
        for (Book book: bookList) {
            System.out.println(book);
        }
        System.out.println("\nПожалуйста отправьте ID книги которую хотите удалить из базы: "  + "  / Главное меню *");
        Scanner scanner = new Scanner(System.in);
        String bookId = scanner.nextLine();
        if (!(bookId.equals("*"))){

            adminImpl.removeBook(Integer.parseInt(bookId));
            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + "Главное меню *");
                switch (scanner.nextLine()) {
                    case "*":
                        exitMenu = false;
                        break;
                    default:
                        System.out.println("Не правильно ввели");
                }
            }
        }
    }

    public void changeDetails() throws IOException {
        bookList = adminImpl.printAllBooks();
        if (bookList != null && bookList.size() != 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                for (Book books : bookList) {
                    System.out.println(books);
                }
                System.out.println("Пожалуйста выберите Id книги которую вы хотите изменить: " + "/ Главное меню *");
                String id = scanner.nextLine();

                if (!id.equals("*")){
                    if (adminImpl.checkStatus(Integer.parseInt(id))) {
                        System.out.println("Нажмите 1 чтобы изменить автора или 2 чтобы изменить название книги: ");
                        int num = scanner.nextInt();
                        switch (num) {
                            case 1:
                                System.out.println("Пожалуйста введите нового автора: ");
                                scanner.nextLine();
                                String author = scanner.nextLine();
                                book = adminImpl.changeDetails(Constants.AUTHOR, Integer.parseInt(id), author);
                                System.out.println(book != null ? "Новый результат: " + book : "Мы не смогли сделать изменение");
                                break;
                            case 2:
                                System.out.println("Пожалуйста введите новое название: ");
                                scanner.nextLine();
                                String name = scanner.nextLine();
                                book = adminImpl.changeDetails(Constants.NAME, Integer.parseInt(id), name);
                                System.out.println(book != null ? "Новый результат: " + book : "Мы не смогли сделать изменение");
                                break;
                            default:
                                System.out.println("Неправильный ввели. Попробуйте еще раз");
                                break;
                        }
                    } else {
                        System.out.println("Извините у нас сейчас нет книг");
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void searchBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название или ключивое слово для поиска книги ? " + " / Главное меню *");
        String keyword = scanner.nextLine();
        if (!keyword.equals("*")){
          bookList = adminImpl.searchBook(keyword);
           if (bookList.size() != 0 && bookList != null){
               for (Book book: bookList) {
                   System.out.println(book);
               }
           }
            exitMenu = true;
            while (exitMenu){
                System.out.println("\n" + "Главное меню *");
                switch (scanner.nextLine()){
                    case "*":
                        exitMenu = false;
                        break;
                    default:
                        System.out.println("Не правильно ввели");
                }
            }
        }
    }

    public void searchByGenre(){
        Scanner scanner = new Scanner(System.in);
        String [] genreType = {"Детектив ","Фантастика","Приключения","Роман"};
        System.out.print("Пожалуйста напишите какой жанр вас интересует " + "(");
        for (int i = 0; i < genreType.length; i++) {
            System.out.print(genreType[i]);
            if (genreType.length-1 != i){
                System.out.print(", ");
            } else{
                System.out.print(")" + ":");
            }
        }
        System.out.println();
        String genre = scanner.nextLine();

        List<Book> bookGenre = adminImpl.searchByGenre(genre);
        if (bookGenre != null){
            bookList(bookGenre);
        } else {
            System.out.println("По запросу " + genre + "ничего не найдено. ");
        }
        exitMenu = true;
        while (exitMenu){
            System.out.println("\n" + "Главное меню *");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }
    public void bookList(List<Book> bookList){
        for (Book book: bookList) {
            System.out.println(book);
        }
    }

    public void printMostSold() {
        Scanner scanner = new Scanner(System.in);
        adminImpl.printMostSold();
        exitMenu = true;
        System.out.println();
        while (exitMenu){
            System.out.println("Главное меню * ");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }

    public void addDiscount(){
        adminImpl.addDiscount();
    }

    public void createShelve(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название новой полка");
        String shelveType = scanner.nextLine();

        adminImpl.createShelve(shelveType);
    }

    public void buyBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Название книги которую вы хотите купить: ");
        String bookName = scanner.nextLine();
        List<Book> searchResult = adminImpl.searchBook(bookName);


        if (searchResult != null && searchResult.size() != 0) {
            System.out.println("Резултаты поиска: ");
            bookList(searchResult);

            while (true) {
                System.out.println("Пожалуйста введите ID книги которую вы хотите купить: ");
                String bookId = scanner.nextLine();
                if (Integer.parseInt(bookId) > searchResult.get(searchResult.size() - 1).getId() || Integer.parseInt(bookId) < 1) {
                    System.out.println("Вы неправильно ввели ID книги! Попробуйте еще раз пожалуйста");
                } else {
                    System.out.println("Как вас зовут? ");
                    String userName = scanner.nextLine();
                    try {
                        adminImpl.saleBook(Integer.parseInt(bookId), userName, searchResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } else {
            System.out.println("Извините мы не нашли ничего под вашим запросом!");
        }
    }
}
