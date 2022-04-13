package com.company.signIn;

import com.company.entities.User;

public interface SignInService {

    User checkForUser(String login, String password);

    String getUserId();

    User getUser();
}
