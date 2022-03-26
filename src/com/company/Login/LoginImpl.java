package com.company.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginImpl implements LoginService {
    UserTypes userTypes;
    ArrayList<String[]> users;
    String[] admins;
    String[] info = new String[2];

    public LoginImpl() {
        init();
    }

    public void init()  {
        users = new ArrayList<>();
        fillUsers();
    }

//    public void checkForUser(String login , String password) {
//        for (int i = 0; i < users.size(); i = i + 2) {
//            String[] temp = users.get(i);
//
//            if (login.equals(temp[0]) && password.equals(temp[1])) {
//                System.out.println("Successful!");
//            } else {
//                System.out.println("Please try again later!");
//            }
//        }
//    }

    public void fillUsers() {
        File usersFile = new File("src/com/company/Directories/Users.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(usersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            info = word.split(":");
            users.add(info);
        }
    }

    @Override
    public void setLogin() {

    }

    @Override
    public void setPassword() {

    }

    @Override
    public void checkForUser() {

    }
}
