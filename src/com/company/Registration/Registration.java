package com.company.Registration;

import com.company.Constants;

import java.util.Scanner;

public class Registration {
    RegistrationService registrationService;
    public Registration(){
        registrationService = new RegistrationImpl();
        mainMenu();
    }

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в регистрационную форму." + " 1. Начать / 2. Вернуться обратно в главное меню");

        Boolean check = true;
        while (check){
            String choose = scanner.nextLine();
            switch(choose){
                case "1":
                    System.out.println("1.Регистрация");
                        startReg();
                        check = false;
                        break;
                case "2":
                    check = false;
                    break;
                default:
                    System.out.println("Вы неправильно ввели. Попровуйте еще раз");
                    break;
            }
        }
    }

    public void startReg() {
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Пожалуйста введите число");

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
