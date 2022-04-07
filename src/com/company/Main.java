package com.company;

import com.company.commonService.Process;
import com.company.panels.adminPanel.AdminMenu;
import com.company.panels.userPanel.UserImpl;
import com.company.panels.userPanel.UserMenu;
import com.company.panels.userPanel.UserService;
import com.company.signIn.Login;
import com.company.signIn.LoginImpl;
import com.company.signIn.LoginService;
import com.company.signUp.Registration;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;


public class Main {
    public static void main(String[] args) {
        RegistrationService registrationService= new RegistrationImpl();
        Registration registration= new Registration(registrationService);

        LoginService loginService= new LoginImpl();

        UserService userService = new UserImpl();
        UserMenu userMenu = new UserMenu(userService);

        AdminMenu adminMenu = new AdminMenu();
        Login login = new Login(loginService, userMenu, adminMenu);

        Process process = new Process(registration, login);
        process.startMainMenu();

   }
}
