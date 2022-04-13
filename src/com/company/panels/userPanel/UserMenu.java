package com.company.panels.userPanel;

import com.company.constants.CommonConstants;
import com.company.constants.UserConstant;
import com.company.entities.Book;
import com.company.constants.Constants;
import com.company.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserMenu implements UserConstant { // remove interface constants
    private UserService userService;
    private String[] userCredentials;
    private User user;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }


    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(CommonConstants.MENU);
            System.out.println(CommonConstants.ONE + " " + MY_PROFILE);
            System.out.println(CommonConstants.TWO + " " + NEW_BOOKS);
            System.out.println(CommonConstants.THREE + " " + CommonConstants.SEE_ALL_BOOKS);
            System.out.println(CommonConstants.FOUR + " " + CommonConstants.SEARCH_BY_GENRE);
            System.out.println(CommonConstants.FIVE + " " + CommonConstants.SEARCH);
            System.out.println(CommonConstants.SIX + " " + BUY_BOOK);
            System.out.println(CommonConstants.SEVEN + " " + MY_ORDERS);
            System.out.println(CommonConstants.EIGHT + " " + BASKET);
            System.out.println(CommonConstants.NINE + " " + NEWS);

            String menuNum = scanner.nextLine();

            switch (menuNum) {
                case CommonConstants.ONE:
                    System.out.println(CommonConstants.ONE + " " + MY_PROFILE);
                    myProfile();
                    break;
                case CommonConstants.THREE:
                    System.out.println(CommonConstants.THREE + " " + CommonConstants.SEE_ALL_BOOKS);
                    seeAllBooks();
                    break;
                case CommonConstants.SIX:
                    System.out.println(CommonConstants.SIX + " " + BUY_BOOK);
                    buyBook();
                default:
                    System.out.println(CommonConstants.TRY_AGAIN);
                    break;
            }
        }
    }

    public void welcomeUser() {
        System.out.println(CommonConstants.WELCOME + getUser().getName() + " " + getUser().getSurname() + "!");
        System.out.println("Your id is" + getUser().getId());
    }

    public void myProfile() {
        userCredentials = userService.userProfile(getUser().getId());

        System.out.println("1.ID: " + userCredentials[Constants.USER_ID]);
        System.out.println("2.Имя: " + userCredentials[Constants.USER_NAME]);
        System.out.println("3.Фамилия: " + userCredentials[Constants.USER_SURNAME]);
        System.out.println("4.Возрасть: " + userCredentials[Constants.USER_AGE]);
        System.out.println("5.Логин: " + userCredentials[Constants.USER_LOGIN]);
        System.out.println("6.Пароль: " + userCredentials[Constants.USER_PASSWORD]);

        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("1. Изменить детали / * Главное меню");
            String exitMenu = scanner.nextLine();
            switch (exitMenu) {
                case "1":
                    System.out.println("1. Изменить детали");
                    changeCredentials();
                    break;
                case "*":
                    check = false;
                    break;
                default:
                    System.out.println("Попробуйте сного!");
                    break;
            }
        }
    }

    public void changeCredentials() {
        Scanner scanner = new Scanner(System.in);
        boolean menuLoop = true;
        String exitSymbol = "*";
        int minNum = 1;
        int maxNum = 7;

        while (menuLoop) {
            System.out.println("Что хотели изменить? (Отправьте число) / * Главное меню");
            String oldValue = scanner.nextLine();

            if (!oldValue.equals(exitSymbol)
                    && Integer.parseInt(oldValue) > minNum && Integer.parseInt(oldValue) < maxNum) {

                System.out.println("На что хотели поменять?");
                String newValue = scanner.nextLine();

                userService.changeCredentials(Integer.parseInt(oldValue), newValue);
                menuLoop = false;
                break;
            } else if (oldValue.equals(exitSymbol)) {
                menuLoop = false;
                break;
            } else {
                System.out.println("Не правильно ввели.Попробуйте еще раз!");
            }
        }
    }

    public void seeAllBooks() {
        List<Book> allBooks = userService.printAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(CommonConstants.MAIN_MENU);
            String exitSymbol = scanner.nextLine();

            if (exitSymbol.equals(CommonConstants.STAR))
                break;
            else
                System.out.println(CommonConstants.TRY_AGAIN);
        }
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void buyBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CommonConstants.BOOK_NAME + ":");
        String bookName = scanner.nextLine();
        List<Book> searchResult = userService.searchBook(bookName);


        if (searchResult != null && searchResult.size() != 0) {
            System.out.println(CommonConstants.RESULT + ":");
            printBookList(searchResult);

            while (true) {
                System.out.println(CommonConstants.BOOK_ID + ":");
                String bookId = scanner.nextLine();
                if (Integer.parseInt(bookId) > searchResult.get(searchResult.size() - 1).getId() || Integer.parseInt(bookId) < 1) {
                    System.out.println(CommonConstants.TRY_AGAIN);
                } else {
                    System.out.println(CommonConstants.NAME + ":");
                    String userName = scanner.nextLine();
                    try {
                        userService.saleBook(Integer.parseInt(bookId), userName, searchResult);
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

    private void printBookList(List<Book> bookList){
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}




