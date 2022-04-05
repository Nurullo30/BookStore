package com.company.entities;

import com.company.commonService.UserRole;

import java.io.IOException;
import java.util.List;

public interface UserDataManager {
    List<User> getUsers();

    void exportUsers() throws IOException;

    String addNewUser(User user, UserRole userType);
}
