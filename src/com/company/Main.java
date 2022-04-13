package com.company;

import com.company.commonService.GuestPage;
import com.company.entities.UserDataBase;
import com.company.panels.adminPanel.AdminImpl;
import com.company.panels.adminPanel.AdminMenu;
import com.company.panels.adminPanel.AdminService;
import com.company.panels.userPanel.UserImpl;
import com.company.panels.userPanel.UserMenu;
import com.company.panels.userPanel.UserService;
import com.company.signIn.SignIn;
import com.company.signIn.SignInImpl;
import com.company.signIn.SignInService;
import com.company.signUp.Registration;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;

// architechture of dependencies , relationships between classes
public class Main {
    public static void main(String[] args) {
        UserDataBase userDataBase = new UserDataBase();

        RegistrationService registrationService= new RegistrationImpl(userDataBase);
        Registration registration= new Registration(registrationService);

        SignInService signInService = new SignInImpl(userDataBase);

        UserService userService = new UserImpl();
        UserMenu userMenu = new UserMenu(userService);

        AdminService adminService = new AdminImpl();
        AdminMenu adminMenu = new AdminMenu(adminService);
        SignIn signIn = new SignIn(signInService, userMenu, adminMenu); // change SignIn class name

        GuestPage guestpage = new GuestPage(registration, signIn); // Change process class name
        guestpage.startMainMenu();


   }

}
