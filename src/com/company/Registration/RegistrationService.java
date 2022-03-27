package com.company.Registration;

import com.company.Login.Users;

import java.util.List;

public interface RegistrationService {

    String registration(String name, String surname, String age , String login, String password);

    List<Users> getAllUsers();
}
