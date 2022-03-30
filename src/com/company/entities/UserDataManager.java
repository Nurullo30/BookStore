package com.company.entities;

import com.company.commonService.UserRole;

import java.io.IOException;
import java.util.List;

public interface UserDataManager {
    List<Users> getUsers();

    void exportUsers() throws IOException;

    String addNewUser(Users user, UserRole userType);
}
