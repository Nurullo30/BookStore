package com.company.Login.UserPanel;

import com.company.Constants;
import com.company.DataBase.UserDataBase;
import com.company.Login.Users;

import java.util.HashMap;
import java.util.List;

public class UserMenuImpl implements UserMenuService{
    private UserDataBase userDataBase;
    private Users user = new Users();
    public UserMenuImpl(Users user){
        userDataBase = new UserDataBase();
        this.user = user;
    }

    @Override
    public String[] userProfile() {
        String [] userCredentials = {user.getId(), user.getName(), user.getSurname(),
                user.getAge(), user.getLogin(), user.getPassword()};

        return userCredentials;
    }

    public void changeCredentials(String userDetail){
        for (String num: Constants.numbers) {
            switch (Integer.parseInt(num)){
                case Constants.USER_ID:

                case Constants.USER_NAME:
                case Constants.USER_SURNAME:
                case Constants.USER_AGE:
                case Constants.USER_LOGIN:
                case Constants.USER_PASSWORD:
            }
        }
    }

    @Override
    public void setUser(Users user) {
        this.user = user;
    }


    @Override
    public void buyBook() {

    }


}
