package com.company;

import com.company.commonService.Process;
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
        Login login = new Login(loginService);

        Process process = new Process(registration, login);
        process.startMainMenu();

   }
}
