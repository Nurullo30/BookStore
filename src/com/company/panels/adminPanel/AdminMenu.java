package com.company.panels.adminPanel;

import com.company.constants.AdminConstant;
import com.company.constants.CommonConstants;
import com.company.constants.UserConstant;
import com.company.entities.Book;
import com.company.constants.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements AdminConstant {
    private AdminService adminService;
    private List<Book> bookList;
    private boolean exitMenu;

    public AdminMenu(AdminService adminService) {
        this.adminService = adminService;
    }


    public void startMenu() {
        while (true) {
            System.out.println(WELCOME_ADMIN);
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + CommonConstants.ONE + " " + CommonConstants.ADD_BOOK); // Цыфры с переди
            System.out.println(CommonConstants.TWO + " " + DELETE_BOOK);
            System.out.println(CommonConstants.THREE + " " + CommonConstants.CHANGE_BOOK);
            System.out.println(CommonConstants.FOUR + " " + CommonConstants.SEARCH);
            System.out.println(CommonConstants.FIVE + " " + CommonConstants.SEARCH_BY_GENRE);
            System.out.println(CommonConstants.SIX + " " + CommonConstants.SEE_ALL_BOOKS);
            System.out.println(CommonConstants.SEVEN + " " + MOST_SOLD);
            System.out.println(CommonConstants.EIGHT + " " + LEAST_SOLD);
            System.out.println(CommonConstants.NINE + " " + ADD_NEW_SHELF + "\n");
            System.out.println(CommonConstants.CHOOSE_FUNCTION);
            boolean check = true;
            while (check) {
                try {
                    int menuNumber = scanner.nextInt();
                    switch (menuNumber) {
                        case 1:
                            System.out.println(CommonConstants.ONE + " " + CommonConstants.ADD_BOOK);
                            addBook();
                            check = false;
                            break;
                        case 2:
                            System.out.println(CommonConstants.TWO + " " + DELETE_BOOK);
                            removeBook();
                            check = false;
                            break;
                        case 3:
                            System.out.println(CommonConstants.THREE + " " + CommonConstants.CHANGE_BOOK);
                            changeDetails();
                            check = false;
                            break;
                        case 4:
                            System.out.println(CommonConstants.FOUR + " " + CommonConstants.SEARCH);
                            searchBook();
                            check = false;
                            break;
                        case 5:
                            System.out.println(CommonConstants.FIVE + " " + CommonConstants.SEARCH_BY_GENRE);
                            searchByGenre();
                            check = false;
                            break;
                        case 6:
                            System.out.println(CommonConstants.SIX + " " + CommonConstants.SEE_ALL_BOOKS);
                            printAllBooks();
                            check = false;
                            break;
                        case 7:
                            System.out.println(CommonConstants.SEVEN + " " + MOST_SOLD);
                            printMostSold();
                            check = false;
                            break;
                        default:
                            System.out.println(CommonConstants.TRY_AGAIN);
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        boolean checkAddMenu = true;
        while (checkAddMenu) {

            System.out.println(CommonConstants.BOOK_NAME + ": " + CommonConstants.MAIN_MENU);
            String name = scanner.nextLine();
            if (name.equals(CommonConstants.STAR))
                break;

            System.out.println(CommonConstants.BOOK_AUTHOR + ": " + CommonConstants.MAIN_MENU);
            String author = scanner.nextLine();
            if (author.equals(CommonConstants.STAR))
                break;

            System.out.println(CommonConstants.BOOK_GENRE + ": " + CommonConstants.MAIN_MENU);
            String genre = scanner.nextLine();
            if (genre.equals(CommonConstants.STAR))
                break;

            System.out.println(CommonConstants.BOOK_PRICE + ": " + CommonConstants.MAIN_MENU);
            String price = "";
            while (true) {
                price = scanner.nextLine();
                if (numCheck(price)) {
                    break;
                } else {
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
            if (price.equals(CommonConstants.STAR))
                break;

            System.out.println(CommonConstants.BOOK_AMOUNT + ": " + CommonConstants.MAIN_MENU);
            String amount;
            while (true) {
                amount = scanner.nextLine();
                if (numCheck(amount)) {
                    break;
                } else {
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
            if (amount.equals(CommonConstants.STAR))
                break;

            Book book = adminService.addBook(name, author, genre, Integer.parseInt(price), Integer.parseInt(amount));

            System.out.println(book != null ? (CommonConstants.SUCCESSFULLY_ADDED + "\n" + book + "\n")
                    : (CommonConstants.FAILED_ADDING + " \n"));

            exitMenu = true;
            while (exitMenu) {
                System.out.println(CommonConstants.ONE + " " + CommonConstants.ADD_BOOK + "/ *" + CommonConstants.MAIN_MENU);
                switch (scanner.nextLine()) {
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

    public boolean numCheck(String userValue) {

        String[] numbers = {CommonConstants.ZERO, CommonConstants.ONE, CommonConstants.TWO, CommonConstants.THREE, CommonConstants.FOUR, CommonConstants.FIVE,
                CommonConstants.SIX, CommonConstants.SEVEN, CommonConstants.EIGHT, CommonConstants.NINE,};
        for (int i = 0; i < userValue.length(); i++) {
            for (int j = 0; j < numbers.length; j++) {
                String letter = "" + userValue.charAt(i);
                if (letter.equals(numbers[j])) {
                    break;
                } else if (numbers.length - 1 == j) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printAllBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of our all books: ");
        bookList = adminService.printAllBooks();
        for (Book books : bookList) {
            System.out.println(books);
        }
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

    public void removeBook() {
        bookList = adminService.printAllBooks();
        for (Book book : bookList) {
            System.out.println(book);
        }
        System.out.println("\n Пожалуйста отправьте ID книги которую хотите удалить из базы: " + "  /" + CommonConstants.MAIN_MENU);
        Scanner scanner = new Scanner(System.in);
        String bookId = scanner.nextLine();
        if (!(bookId.equals("*"))) {

            adminService.removeBook(Integer.parseInt(bookId));
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
        bookList = adminService.printAllBooks();
        if (bookList != null && bookList.size() != 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                for (Book books : bookList) {
                    System.out.println(books);
                }
                System.out.println("Пожалуйста выберите Id книги которую вы хотите изменить: " + " /" + CommonConstants.MAIN_MENU);
                String id = scanner.nextLine();

                if (!id.equals("*")) {
                    if (adminService.checkStatus(Integer.parseInt(id))) {
                        System.out.println(CommonConstants.ONE + "." + CommonConstants.CHANGE_AUTHOR +
                                CommonConstants.TWO + "." + CommonConstants.CHANGE_NAME);
                        int num = scanner.nextInt();
                        Book book;
                        switch (num) {
                            case 1:
                                System.out.println(CommonConstants.NEW_AUTHOR);
                                scanner.nextLine();
                                String author = scanner.nextLine();
                                book = adminService.changeDetails(Constants.AUTHOR, Integer.parseInt(id), author);
                                System.out.println(book != null ? CommonConstants.NEW_RESULT + ":" +
                                        book : CommonConstants.CHANGES_FAILED);
                                break;
                            case 2:
                                System.out.println(CommonConstants.NEW_NAME + ":");
                                scanner.nextLine();
                                String name = scanner.nextLine();
                                book = adminService.changeDetails(Constants.NAME, Integer.parseInt(id), name);
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

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название или ключивое слово для поиска книги ? " + CommonConstants.MAIN_MENU);
        String keyword = scanner.nextLine();
        if (!keyword.equals(CommonConstants.STAR)) {
            bookList = adminService.searchBook(keyword);
            if (bookList.size() != 0 && bookList != null) {
                for (Book book : bookList) {
                    System.out.println(book);
                }
            }
            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + CommonConstants.MAIN_MENU);
                switch (scanner.nextLine()) {
                    case CommonConstants.STAR:
                        exitMenu = false;
                        break;
                    default:
                        System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void searchByGenre() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(INTERESTED_GENRE + "("
                + CommonConstants.DETECTIVE + "," + CommonConstants.FANTACY + "," +
                CommonConstants.ADVENTURE + "," + CommonConstants.NOVEL + "\n");
        String genre = scanner.nextLine();

        List<Book> bookGenre = adminService.searchByGenre(genre);
        if (bookGenre != null) {
            bookList(bookGenre);
        } else {
            System.out.println(genre + " " + CommonConstants.NOT_FOUND);
        }
        exitMenu = true;
        while (exitMenu) {
            System.out.println("\n" + CommonConstants.MAIN_MENU);
            switch (scanner.nextLine()) {
                case CommonConstants.STAR:
                    exitMenu = false;
                    break;
                default:
                    System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
    }

    public void bookList(List<Book> bookList) {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void printMostSold() {
        Scanner scanner = new Scanner(System.in);
        adminService.printMostSold();
        exitMenu = true;
        System.out.println();
        while (exitMenu) {
            System.out.println(CommonConstants.MAIN_MENU);
            switch (scanner.nextLine()) {
                case CommonConstants.STAR:
                    exitMenu = false;
                    break;
                default:
                    System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
    }

    public void addDiscount() {
        adminService.addDiscount();
    }

    public void createShelve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название новой полка");
        String shelveType = scanner.nextLine();

        adminService.createShelve(shelveType);
    }

    public void buyBook() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Название книги которую вы хотите купить: ");
//        String bookName = scanner.nextLine();
//        List<Book> searchResult = adminService.searchBook(bookName);
//
//
//        if (searchResult != null && searchResult.size() != 0) {
//            System.out.println(CommonConstants.RESULT + ":");
//            bookList(searchResult);
//
//            while (true) {
//                System.out.println("Пожалуйста введите ID книги которую вы хотите купить: ");
//                String bookId = scanner.nextLine();
//                if (Integer.parseInt(bookId) > searchResult.get(searchResult.size() - 1).getId() || Integer.parseInt(bookId) < 1) {
//                    System.out.println("Вы неправильно ввели ID книги! Попробуйте еще раз пожалуйста");
//                } else {
//                    System.out.println("Как вас зовут? ");
//                    String userName = scanner.nextLine();
//                    try {
//                        adminService.saleBook(Integer.parseInt(bookId), userName, searchResult);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//            }
//        } else {
//            System.out.println(CommonConstants.NOT_FOUND);
//        }
//    }
    }
}
