package com.company.Login;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Login {
    private String login;
    private String password;
    public Login() {

    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
