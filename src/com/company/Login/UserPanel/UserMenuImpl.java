package com.company.Login.UserPanel;

import com.company.Constants;
import com.company.DataBase.UserDataBase;
import com.company.DataBase.UserDataManager;
import com.company.Login.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMenuImpl implements UserMenuService{
    private UserDataManager userDataBase;
    private Users currentUser;
    private final String [] userCredentials = new String[7];
    private String userId;

    public UserMenuImpl(String userId){
        this.userId = userId;
        userDataBase = new UserDataBase();
        currentUser = new Users();
    }

    @Override
    public String[] userProfile() {
        List<Users> usersList = userDataBase.getUsers();
        for (Users user: usersList) {
            if (user.getId().equals(userId)){
                currentUser = user;
                userCredentials[Constants.USER_ID] = user.getId();
                userCredentials[Constants.USER_NAME] = user.getName();
                userCredentials[Constants.USER_SURNAME] = user.getSurname();
                userCredentials[Constants.USER_AGE] = user.getAge();
                userCredentials[Constants.USER_LOGIN] = user.getLogin();
                userCredentials[Constants.USER_PASSWORD] = user.getPassword();
            }
        }
        return userCredentials;
    }

    public void changeCredentials(int oldValue, String newValue){
        switch (oldValue){
            case Constants.USER_NAME:
                 currentUser.setName(newValue);
                 break;
            case Constants.USER_SURNAME:
                 currentUser.setSurname(newValue);
                 break;
            case Constants.USER_AGE:
                 currentUser.setAge(newValue);
                 break;
            case Constants.USER_LOGIN:
                 currentUser.setLogin(newValue);
                 break;
            case Constants.USER_PASSWORD:
                 currentUser.setPassword(newValue);
                 break;
        }
        try {
            userDataBase.exportUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void buyBook() {

    }


}
