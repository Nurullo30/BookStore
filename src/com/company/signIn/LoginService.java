package com.company.signIn;

import com.company.entities.User;

public interface LoginService {

    User checkForUser(String login, String password);

    String getUserId();

    User getUser();
}
