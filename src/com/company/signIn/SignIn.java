package com.company.signIn;

import com.company.commonService.UserRole;
import com.company.constants.CommonConstants;
import com.company.entities.User;
import com.company.panels.adminPanel.AdminMenu;
import com.company.panels.userPanel.UserMenu;

import java.util.Scanner;

public class SignIn {
    private SignInService signInService;
    private UserMenu userMenu;
    private AdminMenu adminMenu;
    Scanner scanner;

    public SignIn(SignInService signInService, UserMenu userMenu, AdminMenu adminMenu){
        this.signInService = signInService;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
        scanner = new Scanner(System.in);
    }

    public void checkUser(){
        System.out.println(CommonConstants.WELCOME_LOGIN);
        while (true){
            System.out.println(CommonConstants.LOGIN + ":");
            String login = scanner.nextLine();

            System.out.println(CommonConstants.PASSWORD + ":");
            String password = scanner.nextLine();

            User user = signInService.checkForUser(login, password);

            if (user != null && user.getUserRole().equals(UserRole.USER)){
                userMenu.setUser(user);
                userMenu.startMenu();
                break;
            } else if (user != null && user.getUserRole().equals(UserRole.ADMIN)){
                adminMenu.startMenu();
                break;
            } else {
                System.out.println(CommonConstants.TRY_AGAIN + "\n");
            }
        }
    }

}
