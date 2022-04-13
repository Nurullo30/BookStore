package com.company.panels.userPanel;

import com.company.constants.UserConstant;
import com.company.entities.User;
import com.company.panels.BookStoreImpl;
import com.company.constants.Constants;
import com.company.entities.UserDataBase;
import com.company.entities.UserDataManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserImpl extends BookStoreImpl implements UserService , UserConstant {

    private UserDataManager userDataBase;
    private User currentUser;
    private final String [] userCredentials = new String[7];
    Scanner scanner;
    public UserImpl(){
        userDataBase = new UserDataBase();
        scanner = new Scanner(System.in);
    }

    @Override
    public String[] userProfile(String userId) {
        List<User> userList = userDataBase.getUsers();
        for (User user: userList) {
            if (user.getId().equals(userId)){
                currentUser = user;
                userCredentials[Constants.USER_ID] = user.getId();
                userCredentials[Constants.USER_NAME] = user.getName();
                userCredentials[Constants.USER_SURNAME] = user.getSurname();
                userCredentials[Constants.USER_AGE] = user.getAge();
                userCredentials[Constants.USER_LOGIN] = user.getLogin();
                userCredentials[Constants.USER_PASSWORD] = user.getPassword();
            }
        }
        return userCredentials;
    }

    public void changeCredentials(int oldValue, String newValue){
        switch (oldValue){
            case Constants.USER_NAME:
                 currentUser.setName(newValue);
                 break;
            case Constants.USER_SURNAME:
                 currentUser.setSurname(newValue);
                 break;
            case Constants.USER_AGE:
                 currentUser.setAge(newValue);
                 break;
            case Constants.USER_LOGIN:
                 currentUser.setLogin(newValue);
                 break;
            case Constants.USER_PASSWORD:
                 currentUser.setPassword(newValue);
                 break;
        }
        try {
            userDataBase.exportUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void buyBook() {

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
    }


}
