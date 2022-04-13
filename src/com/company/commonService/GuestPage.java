package com.company.commonService;

import com.company.constants.CommonConstants;
import com.company.signIn.SignIn;
import com.company.signUp.Registration;

import java.util.Scanner;

public class GuestPage {
    private Scanner scanner;
    private Registration registration;
    private SignIn signIn;

    public GuestPage(Registration registration, SignIn signIn){
       this.registration = registration;
       this.signIn = signIn;
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
                    signIn.checkUser();
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
}
