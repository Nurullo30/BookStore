package com.company.signIn;


import com.company.entities.User;
import com.company.entities.UserDataBase;

import java.util.ArrayList;
import java.util.List;

public class SignInImpl implements SignInService {
    private List<User> userList;// change name (User)
    private String userId;
    UserDataBase userDataBase;

    public SignInImpl(UserDataBase userDataBase) {
        userList = new ArrayList<>();
        this.userDataBase = userDataBase;
    }

    @Override
    public User checkForUser(String login, String password) { // change name
       return userDataBase.checkForUser(login, password); // create an exception for user not found
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
