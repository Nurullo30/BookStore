package com.company.CommonService;

import com.company.Login.LoginService;
import com.company.Login.UserTypes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Process{
    private UserTypes userTypes;
    private Scanner scanner;
    private Boolean check;

    public Process(){
        init();
        userTypes = UserTypes.GUEST;
    }

    private void init(){
        scanner = new Scanner(System.in);
        check = true;

        while (check){
            System.out.println("Добро пожаловать на наш онлайн книжный магазин!");
            System.out.println("1. Купить книгу" + " " +" 2. Войти " + "3. Регистрация");

            int inputNumber = scanner.nextInt();
            switch (inputNumber){
                case 1:
                    System.out.println("Вы выбрали Купить книгу");
                    break;
                case 2:
                    System.out.println("Вы выбрали Войти в кабинет");
                    loginPage();
                    check = false;
                    break;
                case 3:
                    System.out.println("Вы выбрали регистрацию");
                    break;
                default:
                    System.out.println("Пожалуйста попробуйте сново");
            }
        }
    }

    public void loginPage(){
        scanner = new Scanner(System.in);

        System.out.println("Ваш Логин: ");
        String login = scanner.nextLine();


        System.out.println("Ваш Пароль");
        String password = scanner.nextLine();

        LoginService loginService = new LoginService();
        try {
            loginService.init(login,password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
