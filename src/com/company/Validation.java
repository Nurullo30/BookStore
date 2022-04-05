package com.company;

import com.company.constants.Constants;
import com.company.entities.User;
import com.company.entities.UserDataBase;

import java.util.List;

public class Validation {
    UserDataBase userDataBase;
    List<User> userList;
    public Validation(){
        userDataBase = new UserDataBase();
    }

    public String checkUserExist(String detail, UserInfoType userInfoType){
        userList = userDataBase.getUsers();
        String resultValue = StringValue.LOGIN_FREE;
        for (User user: userList) {
            switch (userInfoType){
                case LOGIN:
                    if (detail.equals(user.getLogin()))
                        resultValue = StringValue.USER_EXIST;
                    break;
                case PASSWORD:
                    if (detail.equals(user.getPassword()))
                        resultValue = StringValue.USER_EXIST;
                    break;
                case ID:
                    if (detail.equals(user.getId()))
                        resultValue = StringValue.USER_EXIST;
                    break;
                default:
                    return null;
            }
        }
        return resultValue;
    }
}
