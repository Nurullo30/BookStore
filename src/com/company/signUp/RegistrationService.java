package com.company.signUp;

import com.company.entities.Users;

import java.util.List;

public interface RegistrationService {

    String registration(String name, String surname, String age , String login, String password);

    List<Users> getAllUsers();
}
