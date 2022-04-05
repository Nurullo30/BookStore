package com.company.signUp;

import com.company.UserInfoType;
import com.company.entities.User;

import java.util.List;

public interface RegistrationService {

    String registration(String name, String surname, String age, String login, String password);

    List<User> getAllUsers();

    Boolean checkUserExist(String userCredentials, UserInfoType UserInfoType);

}
