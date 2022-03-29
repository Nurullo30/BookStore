package com.company.Login.UserPanel;

import com.company.Login.Users;

import java.util.HashMap;

public interface UserMenuService {
    public void buyBook();

    String[] userProfile();

    void changeCredentials(int oldValue, String newValue);

}
