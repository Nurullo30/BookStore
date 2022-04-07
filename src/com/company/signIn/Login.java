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

    public Login(LoginService loginService, UserMenu userMenu, AdminMenu adminMenu){
        this.loginService = loginService;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
    }

    public void checkUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать! Пожалуйста введите логин и пароль");
        while (true){
            System.out.println("Логин:");
            String login = scanner.nextLine();

            System.out.println("Пароль:");
            String password = scanner.nextLine();

            User user = loginService.checkForUser(login, password);

            if (user != null && user.getUserRole().equals(UserRole.USER)){
                userMenu.setUser(user);
                userMenu.startMenu();
                break;
            } else if (user != null && user.getUserRole().equals(UserRole.ADMIN)){
                adminMenu.startMenu();
                break;
            } else {
                System.out.println("Не правильно ввели. Пожалуйста проверьте еще раз! \n");
            }
        }
    }

}
