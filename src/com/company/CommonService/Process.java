package com.company.CommonService;

import com.company.Login.AdminPanel.Login;
import com.company.Login.LoginImpl;
import com.company.Login.LoginService;
import com.company.Login.UserTypes;
import com.company.Registration.Registration;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Process{
    private UserTypes userTypes;
    private Scanner scanner;
    private Registration registration;

    public Process(){
        userTypes = UserTypes.GUEST;
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
