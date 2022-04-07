package com.company.commonService;

import com.company.constants.CommonConstants;
import com.company.signIn.Login;
import com.company.signUp.Registration;

import java.util.Scanner;

public class Process{
    private Scanner scanner;
    private Registration registration;
    private Login login;

    public Process(Registration registration, Login login){
       this.registration = registration;
       this.login = login;
       scanner = new Scanner(System.in);
    }

    public void startMainMenu(){
        boolean UserInMenu = true;
        System.out.println(CommonConstants.WELCOME_BOOKSTORE);
        while (UserInMenu){
            System.out.println(CommonConstants.ONE + " " + CommonConstants.SIGN_IN +
                        " "  + CommonConstants.TWO + " " + CommonConstants.SIGN_UP);

            int menuNumber = scanner.nextInt();
            switch (menuNumber){
                case 1:
                    System.out.println(CommonConstants.ONE + " " + CommonConstants.SIGN_IN);
                    authorizeUser();
                    UserInMenu = false;
                    break;
                case 2:
                    System.out.println(CommonConstants.TWO + " " + CommonConstants.SIGN_UP);
                    registration.startReg();
                    break;
                default:
                    System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
    }

    public void authorizeUser(){
       login.checkUser();
    }
}
