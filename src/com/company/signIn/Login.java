package com.company.signIn;

import com.company.commonService.UserRole;
import com.company.constants.Constants;
import com.company.entities.Users;
import com.company.panels.adminPanel.AdminMenu;
import com.company.panels.userPanel.UserMenu;
import com.company.signIn.*;

import java.util.Scanner;

public class Login {
    private LoginService loginImpl;
    private UserMenu userMenu;
    private AdminMenu adminMenu;
    private Users user;

    public Login(LoginService loginService){
        loginImpl = loginService;
        loginMenu();

    }

    public void loginMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать! Пожалуйста введите логин и пароль");
        while (true){
            System.out.println("Логин:");
            String login = scanner.nextLine();

            System.out.println("Пароль:");
            String password = scanner.nextLine();

            Users user = new Users();
            user = loginImpl.checkForUser(login, password);

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
