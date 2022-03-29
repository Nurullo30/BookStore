package com.company.Login.AdminPanel;

import com.company.Constants;
import com.company.Login.LoginService;
import com.company.Login.UserPanel.UserMenu;
import com.company.Login.UserPanel.UserMenuImpl;
import com.company.Login.UserPanel.UserMenuService;
import com.company.Registration.RegistrationImpl;
import com.company.Registration.RegistrationService;

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
