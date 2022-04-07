package com.company.signUp;

import com.company.constants.CommonConstants;
import com.company.constants.Constants;

import java.util.Scanner;

public class Registration {
    RegistrationService registrationService;
    Scanner scanner;

    public Registration(RegistrationService registrationService){
        this.registrationService = registrationService;
        scanner = new Scanner(System.in);
    }

    public void startReg() {
        boolean userInRegMenu = true;
        while(userInRegMenu){
            System.out.println(CommonConstants.NAME + ":");
            String name = scanner.nextLine();

            System.out.println(CommonConstants.SURNAME + ":");
            String surname = scanner.nextLine();
            int age = 0;

            boolean ageCheck = true;

            while(ageCheck){
                Scanner ageScanner = new Scanner(System.in);
                System.out.println(CommonConstants.AGE + ":");
                try {
                    age = ageScanner.nextInt();
                    ageCheck = false;
                } catch (Exception e){
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }

            String login ="";
            boolean loginCheck = true;
            while (loginCheck){
                System.out.println(CommonConstants.LOGIN);
                login = scanner.nextLine();

                boolean loginExist = registrationService.checkUserExist(login, UserInfoType.LOGIN);

                if (loginExist){
                    loginCheck = false;
                    break;
                } else {
                    System.out.println(CommonConstants.USER_EXIST);
                }
            }

            System.out.println(CommonConstants.PASSWORD);
            String password = scanner.nextLine();

            String regStatus  = registrationService.registration(name, surname, String.valueOf(age), login , password);


            if (regStatus.equals(Constants.SUCCESSFUL)){
                System.out.println(CommonConstants.REGISTRATION_SUCCESS);
            } else if (regStatus.equals(Constants.FAILED)){
                System.out.println(CommonConstants.REGISTRATION_FAIL);
                userInRegMenu = false;
                break;
            }


            while (true){
                System.out.println(CommonConstants.STAR + " " + CommonConstants.MAIN_MENU);
                String mainMenuNum = scanner.nextLine();

                boolean exit = exitMenu(mainMenuNum);

                if (exit){
                    userInRegMenu = false;
                    break;
                } else {
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public boolean exitMenu(String exitValue){
        if (exitValue.equals(CommonConstants.STAR)){
            return true;
        } else
        return false;
    }
}
