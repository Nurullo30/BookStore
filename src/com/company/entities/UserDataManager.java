package com.company.entities;

import com.company.commonService.UserTypes;

import java.io.IOException;
import java.util.List;

public interface UserDataManager {
    List<Users> getUsers();

    void exportUsers() throws IOException;

    String addNewUser(Users user, UserTypes userType);
}
