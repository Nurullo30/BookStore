package com.company.signIn;

import com.company.entities.Users;

public interface LoginService {

    Users checkForUser(String login, String password);

    String getUserId();

    Users getUser();
}
