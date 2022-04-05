package com.company.signUp;

import com.company.StringValue;
import com.company.UserInfoType;
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
        boolean userInRegMenu = true;
        while(userInRegMenu){
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

            String login ="";
            boolean loginCheck = true;
            while (loginCheck){
                System.out.println(StringValue.LOGIN);
                login = scanner.nextLine();

                boolean loginExist = registrationService.checkUserExist(login, UserInfoType.LOGIN);

                if (loginExist){
                    loginCheck = false;
                    break;
                } else {
                    System.out.println(StringValue.USER_EXIST);
                }
            }

            System.out.println(StringValue.PASSWORD);
            String password = scanner.nextLine();

            String regStatus  = registrationService.registration(name, surname, String.valueOf(age), login , password);


            if (regStatus.equals(Constants.SUCCESSFUL)){
                System.out.println(StringValue.REGISTRATION_SUCCESS);
            } else if (regStatus.equals(Constants.FAILED)){
                System.out.println(StringValue.REGISTRATION_FAIL);
                userInRegMenu = false;
                break;
            }


            while (true){
                System.out.println(StringValue.STAR + " " + StringValue.MAIN_MENU);
                String mainMenuNum = scanner.nextLine();

                boolean exit = exitMenu(mainMenuNum);

                if (exit){
                    userInRegMenu = false;
                    break;
                } else {
                    System.out.println(StringValue.TRY_AGAIN);
                }
            }
        }
    }

    public boolean exitMenu(String exitValue){
        if (exitValue.equals(StringValue.STAR)){
            return true;
        } else
        return false;
    }
}
