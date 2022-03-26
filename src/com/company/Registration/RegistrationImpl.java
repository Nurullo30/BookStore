package com.company.Registration;

import com.company.Constants;
import com.company.DataBase.UserDataBase;
import com.company.Login.UserTypes;
import com.company.Login.Users;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class RegistrationImpl implements RegistrationService{
    private final UserDataBase userDataBase;


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

}
