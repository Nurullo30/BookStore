package com.company.signIn;


import com.company.constants.Constants;
import com.company.entities.UserDataBase;
import com.company.entities.Users;

import java.util.ArrayList;
import java.util.List;

public class LoginImpl implements LoginService {
    private List<Users> usersList;
    private String userId;
    UserDataBase userDataBase;

    public LoginImpl() {
        usersList = new ArrayList<>();
        userDataBase = new UserDataBase();
    }

    @Override
    public String checkForUser(String login, String password) {
        usersList = userDataBase.getUsers();

        for (Users user: usersList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                userId = user.getId();
                return user.getId();
            }
        }
        return Constants.FAILED;
    }

    public String getUserId() {
        return userId;
    }

    public Users getUser(){
        for (Users user: usersList) {
            if (user.getId().equals(userId) && userId != null){
                return user;
            }
        }
        return null;
    }
}
