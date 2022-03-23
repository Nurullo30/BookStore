package com.company.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {
    UserTypes userTypes;
    ArrayList<String[]> users;
    String[] admins;
    String[] info = new String[2];

    public Validation() throws FileNotFoundException {
        init();
    }

    public void init() throws FileNotFoundException {
        users = new ArrayList<>();
        fillUsers();
    }

    public void checkForUser(String login , String password) throws FileNotFoundException {
        for (int i = 0; i < users.size(); i = i + 2) {
            String[] temp = users.get(i);

            if (login.equals(temp[0]) && password.equals(temp[1])) {
                System.out.println("Successful!");
            } else {
                System.out.println("Please try again later!");
            }
        }
    }

    public void fillUsers() throws FileNotFoundException {
        File usersFile = new File("src/com/company/Directories/Users.txt");

        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            info = word.split(":");
            users.add(info);
        }


    }
}
