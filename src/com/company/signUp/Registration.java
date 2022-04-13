package com.company.signUp;

import com.company.constants.CommonConstants;
import com.company.constants.Constants;
import com.company.constants.UserConstant;
import java.util.Scanner;

public class Registration implements UserConstant {
    RegistrationService registrationService;
    Scanner scanner;

    public Registration(RegistrationService registrationService){
        this.registrationService = registrationService;
        scanner = new Scanner(System.in);
    }

    public void startReg() { // Change Method length (too long) // Done
        while (true) {
            String name = registerName();
            String surname = registerSurname();
            String age = registerAge();
            String login = registerLogin();
            String password = registerPassword();

            boolean isUerSuccessful = registerUser(name, surname, String.valueOf(age), login, password);

            if (!isUerSuccessful)
                break;

            if (exitMenu())
                break;
        }
    }
    private String registerName(){
        System.out.println(CommonConstants.NAME + ":");
        return scanner.nextLine();
    }

    private String registerSurname(){
        System.out.println(CommonConstants.SURNAME + ":");
        return scanner.nextLine();
    }


    private String registerAge(){
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
        return String.valueOf(age);
    }
    private String registerLogin(){
        String login ="";
        boolean loginCheck = true;
        while (loginCheck){
            System.out.println(CommonConstants.LOGIN);
            login = scanner.nextLine();
            if (registrationService.checkUserExist(login, UserInfoType.LOGIN)){
                loginCheck = false;
                break;
            }
            System.out.println(CommonConstants.USER_EXIST);
        }
       return login;
    }
    public String registerPassword(){
        System.out.println(CommonConstants.PASSWORD);
        return scanner.nextLine();
    }

    private boolean registerUser(String name, String surname, String age, String login, String password){
       String regStatus = registrationService.registration(name, surname, age, login , password);
        if (regStatus.equals(Constants.SUCCESSFUL)){
            System.out.println(REGISTRATION_SUCCESS);
            return true;
        } else {
           System.out.println(REGISTRATION_FAIL);
           return false;
        }
    }

    public boolean exitMenu(){
        while (true) {
            System.out.println(CommonConstants.ONE + " " + CommonConstants.MAIN_MENU);
            String mainMenuNum = scanner.nextLine();
            boolean isMainMenu = mainMenuNum.equals(CommonConstants.ONE);
            if(isMainMenu){
               break;
            }
            System.out.println(CommonConstants.TRY_AGAIN);
        }
        return true;
    }
}
