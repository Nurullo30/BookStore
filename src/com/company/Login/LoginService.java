package com.company.Login;

import java.util.HashMap;

public interface LoginService {

    String checkForUser(String login, String password);

    String getUserId();

    Users getUser();


}
