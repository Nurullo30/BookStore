package com.company.signUp;

import com.company.StringValue;
import com.company.constants.Constants;

import java.util.Scanner;

public class Registration {
    RegistrationService registrationService;
    Scanner scanner;

    public Registration(RegistrationService registrationService){
        this.registrationService = registrationService;
        scanner = new Scanner(System.in);
    }

    public void startMenu(){
        System.out.println(StringValue.WELCOME_REG + " "
                + StringValue.ONE + " "  + StringValue.START + " / "
                + StringValue.STAR + " " + StringValue.MAIN_MENU);
        Boolean UserInMenu = true;
        while (UserInMenu){
            String choose = scanner.nextLine();

            switch(choose){
                case StringValue.ONE:
                    System.out.println(StringValue.SIGN_UP);
                    startReg();
                    UserInMenu = false;
                    break;
                case StringValue.STAR:
                    UserInMenu = false;
                    break;
                default:
                    System.out.println(StringValue.TRY_AGAIN);
                    break;
            }
        }
    }

    public void startReg() {
        boolean UserInRegMenu = true;
        while(UserInRegMenu){
            System.out.println(StringValue.NAME + ":");
            String name = scanner.nextLine();

            System.out.println(StringValue.SURNAME + ":");
            String surname = scanner.nextLine();
            int age = 0;

            boolean ageCheck = true;

            while(ageCheck){
                Scanner ageScanner = new Scanner(System.in);
                System.out.println(StringValue.AGE + ":");
                try {
                    age = ageScanner.nextInt();
                    ageCheck = false;
                } catch (Exception e){
                    System.out.println(StringValue.TRY_AGAIN);
                }
            }

            System.out.println(StringValue.LOGIN);
            String login = scanner.nextLine();
            System.out.println(StringValue.PASSWORD);
            String password = scanner.nextLine();

            String regStatus  = registrationService.registration(name, surname, String.valueOf(age), login , password);


            if (regStatus.equals(Constants.SUCCESSFUL)){
                System.out.println(StringValue.REGISTRATION_SUCCESS);

                boolean exitCheck = true;
                while (exitCheck){
                    System.out.println(StringValue.STAR + " " + StringValue.MAIN_MENU);
                    String mainMenuNum = scanner.nextLine();

                    if (mainMenuNum.equals(StringValue.STAR)){
                        UserInRegMenu = false;
                        exitCheck = false;
                        break;
                    } else {
                        System.out.println(StringValue.TRY_AGAIN);
                    }
                }
            } else if (regStatus.equals(Constants.FAILED)){
                System.out.println(StringValue.REGISTRATION_FAIL);
                UserInRegMenu = false;
                break;
            }
        }
    }
}
