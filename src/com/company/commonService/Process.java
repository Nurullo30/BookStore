package com.company.commonService;

import com.company.NumericValue;
import com.company.StringValue;
import com.company.signIn.Login;
import com.company.signIn.LoginImpl;
import com.company.signUp.Registration;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;

import java.util.Scanner;

public class Process{
    private Scanner scanner;
    private Registration registration;
    private RegistrationService registrationService;

    public Process(RegistrationService registrationService, Registration registration){
       this.registrationService = registrationService;
       this.registration = registration;
       scanner = new Scanner(System.in);
    }

    public void startMainMenu(){
        boolean isUserInMenu = true;
        System.out.println(StringValue.WELCOME_BOOKSTORE);
        while (isUserInMenu){
            System.out.println(NumericValue.ONE + " " + StringValue.SIGN_IN +
                        " "  + NumericValue.TWO + " " + StringValue.SIGN_UP);

            int menuNumber = scanner.nextInt(); //change name
            switch (menuNumber){
                case 1:
                    System.out.println(NumericValue.ONE + " " + StringValue.SIGN_IN);
                    authorizeUser();
                    isUserInMenu = false;
                    break;
                case 2:
                    System.out.println(NumericValue.TWO + " " + StringValue.SIGN_UP);
                    registration.startMenu();
                    break;
                default:
                    System.out.println(StringValue.TRY_AGAIN);
            }
        }
    }

    public void authorizeUser(){
        Login login = new Login(new LoginImpl());
    }
}
