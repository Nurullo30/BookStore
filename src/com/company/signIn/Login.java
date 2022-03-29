package com.company.signIn;

import com.company.constants.Constants;
import com.company.panels.userPanel.UserMenu;
import com.company.signIn.*;

import java.util.Scanner;

public class Login {
    LoginService loginImpl;
    UserMenu userMenu;


    public Login(LoginService loginService){
        loginImpl = loginService;
        loginMenu();

    }

    public void loginMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать! Пожалуйста введите логин и пароль");

        System.out.println("Логин:");
        String login = scanner.nextLine();

        System.out.println("Пароль:");
        String password = scanner.nextLine();

        String loginCheck = loginImpl.checkForUser(login, password);

        if (!loginCheck.equals(Constants.FAILED)){
            userMenu = new UserMenu(loginCheck);
        }
    }

}
