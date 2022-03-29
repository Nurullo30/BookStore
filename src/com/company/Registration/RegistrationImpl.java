package com.company.Registration;

import com.company.Constants;
import com.company.DataBase.UserDataBase;
import com.company.DataBase.UserDataManager;
import com.company.Login.UserTypes;
import com.company.Login.Users;

import java.util.List;

public class RegistrationImpl implements RegistrationService{
    private final UserDataManager userDataBase;

    public RegistrationImpl(){
        userDataBase = new UserDataBase();
    }

    @Override
    public String registration(String name, String surname, String age, String login, String password) {
            if (name != null && surname != null && age != null){
                Users user = new Users("U"+(userDataBase.getUsers().size() + 1), name, surname, age , login , password);

                String addUser = userDataBase.addNewUser(user, UserTypes.USER);
                if (addUser.equals(Constants.SUCCESSFUL)){
                    return Constants.SUCCESSFUL;
                }
            }
        return Constants.FAILED;
    }

    public List <Users> getAllUsers(){
       return userDataBase.getUsers();
    }

}
