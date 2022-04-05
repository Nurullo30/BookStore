package com.company.signIn;

import com.company.commonService.UserRole;
import com.company.entities.User;
import com.company.panels.adminPanel.AdminMenu;
import com.company.panels.userPanel.UserMenu;

import java.util.Scanner;

public class Login {
    private LoginService loginService;
    private UserMenu userMenu;
    private AdminMenu adminMenu;
    private User user;

    public Login(LoginService loginService){
        loginService = loginService;
    }

    public void checkUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать! Пожалуйста введите логин и пароль");
        while (true){
            System.out.println("Логин:");
            String login = scanner.nextLine();

            System.out.println("Пароль:");
            String password = scanner.nextLine();

            User user = new User();
            user = loginService.checkForUser(login, password);

            if (user != null && user.getUserRole().equals(UserRole.USER)){
                userMenu = new UserMenu(user);
                break;
            } else if (user != null && user.getUserRole().equals(UserRole.ADMIN)){
                adminMenu = new AdminMenu(user);
                break;
            } else {
                System.out.println("Не правильно ввели. Пожалуйста проверьте еще раз! \n");
            }
        }
    }

}
