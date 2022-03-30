package com.company.commonService;

import com.company.signIn.Login;
import com.company.signIn.LoginImpl;
import com.company.signUp.Registration;

import java.util.Scanner;

public class Process{
    private Scanner scanner;
    private Registration registration;

    public Process(){
        mainMenu();
    }

    private void mainMenu(){
        scanner = new Scanner(System.in);
        boolean check = true;
        System.out.println("Добро пожаловать на наш онлайн книжный магазин!");
        while (check){
            System.out.println(" 1. Войти " + "2. Регистрация");

            int inputNumber = scanner.nextInt();
            switch (inputNumber){
                case 1:
                    System.out.println("Вы выбрали Войти в кабинет");
                    loginPage();
                    check = false;
                    break;
                case 2:
                    System.out.println("Вы выбрали регистрацию");
                    registration = new Registration();
                    break;
                default:
                    System.out.println("Пожалуйста попробуйте сново");
            }
        }
    }

    public void loginPage(){
        Login login = new Login(new LoginImpl());
    }
}
