package com.company.signIn;


import com.company.entities.User;
import com.company.entities.UserDataBase;

import java.util.ArrayList;
import java.util.List;

public class LoginImpl implements LoginService {
    private List<User> userList;
    private String userId;
    UserDataBase userDataBase;

    public LoginImpl() {
        userList = new ArrayList<>();
        userDataBase = new UserDataBase();
    }

    @Override
    public User checkForUser(String login, String password) {
        User user  = new User();
        user = userDataBase.checkForUser(login,password);
        if (user != null)
            return user;

        return null;
    }


//        userList = userDataBase.getUsers();
//
//        for (User user: userList) {
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
//                userId = user.getId();
//                return user.getId();
//            }
//        }
//        return Constants.FAILED;


    public String getUserId() {
        return userId;
    }

    public User getUser(){
        for (User user: userList) {
            if (user.getId().equals(userId) && userId != null){
                return user;
            }
        }
        return null;
    }
}
