package com.company.DataBase;

import com.company.Login.UserTypes;
import com.company.Login.Users;

import java.io.IOException;
import java.util.List;

public interface UserDataManager {
    List<Users> getUsers();

    void exportUsers() throws IOException;

    String addNewUser(Users user, UserTypes userType);
}
