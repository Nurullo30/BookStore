package com.company.panels.userPanel;

import com.company.entities.Book;
import com.company.constants.Constants;
import com.company.entities.Users;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private UserService userService;
//    private final String myProfile= "Мой профиль";
    private final String newBooks= "Новые книги";
//    private final String seeAllBooks= "Посмотреть все книги";
    private final String seeByGenre= "Посмотреть по жанрам";
    private final String searchBook= "Искать";
    private final String buyBook= "Купить";
    private final String myOrders= "Мои заказы";
    private final String basket= "Корзина";
    private final String news= "Новости";
    private String [] userCredentials;

    public UserMenu(Users user){
        userService = new UserImpl(user.getId());
        mainMenu();
    }

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Меню:");
            System.out.println("1." + "Мой профиль");
            System.out.println("2." + "Новые книги");
            System.out.println("3." + "Посмотреть все книги");
            System.out.println("4." + seeByGenre);
            System.out.println("5." + searchBook);
            System.out.println("6." + buyBook);
            System.out.println("7." + myOrders);
            System.out.println("8." + basket);
            System.out.println("9." + news);

            int menuNum = scanner.nextInt();
            switch (menuNum){
                case Constants.MY_PROFILE:
                    System.out.println("1." + "Мой профиль");
                    myProfile();
                    break;
                case Constants.SEE_ALL_BOOKS:
                    System.out.println("3." + "Посмотреть все книги");
                    seeAllBooks();
                    break;
                default:
                    System.out.println("Не правильно ввели. Попробуйте еще раз!");
                    break;
            }
        }
    }
    public void welcomeUser(Users user){
        System.out.println("Добро пожаловать," + user.getName() + " " + user.getSurname() + "!");
        System.out.println("Your id is " + user.getId());
    }

    public void myProfile(){
        userCredentials = userService.userProfile();

        System.out.println("1.ID: " + userCredentials[Constants.USER_ID]);
        System.out.println("2.Имя: " + userCredentials[Constants.USER_NAME]);
        System.out.println("3.Фамилия: " + userCredentials[Constants.USER_SURNAME]);
        System.out.println("4.Возрасть: " + userCredentials[Constants.USER_AGE]);
        System.out.println("5.Логин: " + userCredentials[Constants.USER_LOGIN]);
        System.out.println("6.Пароль: " + userCredentials[Constants.USER_PASSWORD]);

        Scanner scanner = new Scanner(System.in);
        boolean check = true;
            while(check){
                System.out.println("1. Изменить детали / * Главное меню");
                String exitMenu = scanner.nextLine();
                switch (exitMenu){
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
    public void changeCredentials(){
        Scanner scanner = new Scanner(System.in);
        boolean menuLoop = true;
        String exitSymbol = "*";
        int minNum = 1;
        int maxNum = 7;

        while (menuLoop){
            System.out.println("Что хотели изменить? (Отправьте число) / * Главное меню");
            String oldValue = scanner.nextLine();

            if (!oldValue.equals(exitSymbol)
                    && Integer.parseInt(oldValue) > minNum && Integer.parseInt(oldValue) < maxNum){

                System.out.println("На что хотели поменять?");
                String newValue = scanner.nextLine();

                userService.changeCredentials(Integer.parseInt(oldValue), newValue);
                menuLoop= false;
                break;
            } else if (oldValue.equals(exitSymbol)){
                menuLoop= false;
                break;
            } else {
                System.out.println("Не правильно ввели.Попробуйте еще раз!");
            }
        }
    }

    public void seeAllBooks(){
        List<Book> allBooks = userService.seeAllBooks();
        for (Book book: allBooks) {
            System.out.println(book);
        }

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("* Главное меню");
            String exitSymbol = scanner.nextLine();

            if (exitSymbol.equals("*"))
                break;
            else
                System.out.println("Не правильно ввели. Попробуйте сного!");
        }
    }






}



//    public void buyBook() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Название книги которую вы хотите купить: ");
//        String bookName = scanner.nextLine();
//        List<Book> searchResult = userService.searchBook(bookName);
//
//
//        if (searchResult != null && searchResult.size() != 0) {
//            System.out.println("Резултаты поиска: ");
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
//                        bookStoreImpl.saleBook(Integer.parseInt(bookId), userName, searchResult);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//            }
//        } else {
//            System.out.println("Извините мы не нашли ничего под вашим запросом!");
//        }
//    }
