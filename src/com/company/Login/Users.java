package com.company.Login;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private String id;
    private String name;
    private String surname;
    private String age;
    private String login;
    private String password;

    public Users(String id, String name, String surname, String age, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public Users(){

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
