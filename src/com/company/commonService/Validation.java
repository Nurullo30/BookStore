package com.company.commonService;

import com.company.constants.CommonConstants;
import com.company.entities.User;
import com.company.entities.UserDataBase;
import com.company.signUp.UserInfoType;

import java.util.List;

public class Validation {
    UserDataBase userDataBase;
    List<User> userList;
    public Validation(){
        userDataBase = new UserDataBase();
    }

    public String checkUserExist(String detail, UserInfoType userInfoType){
        userList = userDataBase.getUsers();
        String resultValue = CommonConstants.LOGIN_FREE;
        for (User user: userList) {
            switch (userInfoType){
                case LOGIN:
                    if (detail.equals(user.getLogin()))
                        resultValue = CommonConstants.USER_EXIST;
                    break;
                case PASSWORD:
                    if (detail.equals(user.getPassword()))
                        resultValue = CommonConstants.USER_EXIST;
                    break;
                case ID:
                    if (detail.equals(user.getId()))
                        resultValue = CommonConstants.USER_EXIST;
                    break;
                default:
                    return null;
            }
        }
        return resultValue;
    }
}
