package com.company.signUp;

import com.company.NumericValue;
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
                + NumericValue.ONE + " "  + StringValue.START + " / "
                + NumericValue.STAR + " " + StringValue.MAIN_MENU);
        Boolean check = true;
        while (check){
            String choose = scanner.nextLine();
            switch(choose){
                case "1":
                    System.out.println("1.Регистрация");
                        startReg();
                        check = false;
                        break;
                case "*":
                    check = false;
                    break;
                default:
                    System.out.println("Вы неправильно ввели. Попровуйте еще раз");
                    break;
            }
        }
    }

    public void startReg() {
        boolean check = true;
        while(check){
            System.out.println("Имя: ");
            String name = scanner.nextLine();

            System.out.println("Фамилия: ");
            String surname = scanner.nextLine();
            int age;
            try {
                System.out.println("Возрасть: ");
                age = scanner.nextInt();
            } catch (Exception e){
                System.out.println(StringValue.TRY_AGAIN);
                break;
            }
            scanner.nextLine();
            System.out.println("Логин: ");
            String login = scanner.nextLine();
            System.out.println("Пароль: ");
            String password = scanner.nextLine();

            String regStatus  = registrationService.registration(name, surname, String.valueOf(age), login , password);

            if (regStatus.equals( Constants.SUCCESSFUL)){
                System.out.println("Вы успешно зарегистрировались! :)");
                check = false;
                break;
            } else if (regStatus.equals(Constants.FAILED)){
                System.out.println("Регистрация не прошло успешно! :( ");
                check = false;
                break;
            }
        }
    }

}
