package com.company.signUp;

import com.company.constants.CommonConstants;
import com.company.commonService.Validation;
import com.company.constants.Constants;
import com.company.entities.User;
import com.company.entities.UserDataBase;
import com.company.entities.UserDataManager;
import com.company.commonService.UserRole;
import java.util.List;

public class RegistrationImpl implements RegistrationService{
    private UserDataManager userDataBase;
    private Validation validation;

    public RegistrationImpl(UserDataBase userDataBase){
        this.userDataBase = userDataBase;
        validation = new Validation();
    }

    @Override
    public String registration(String name, String surname, String age, String login, String password) {

        User user = new User("U" + (getAllUsers().size() + 1), name, surname, age, login, password);

        String newUser = userDataBase.addNewUser(user, UserRole.USER);

        if (newUser.equals(Constants.SUCCESSFUL)) {
            return Constants.SUCCESSFUL;
        } else
            return Constants.FAILED;
    }

    public Boolean checkUserExist(String detail, UserInfoType userInfoType){
        if (validation.checkUserExist(detail, userInfoType).equals(CommonConstants.LOGIN_FREE)){
            return true;
        }
        return false;
    }



    public List <User> getAllUsers(){
       return userDataBase.getUsers();
    }

}
