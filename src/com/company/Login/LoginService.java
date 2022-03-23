package com.company.Login;

import java.io.FileNotFoundException;

public class LoginService {
    private Validation validation;
    private Login loginPage;

    public LoginService(){
        loginPage = new Login();
    }

    public void init(String login, String password) throws FileNotFoundException {
        try {
            validation = new Validation();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        loginPage.setLogin(login);
        loginPage.setPassword(password);
        validation.checkForUser(loginPage.getLogin(), loginPage.getPassword());
    }

}
