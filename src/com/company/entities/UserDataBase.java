package com.company.entities;

import com.company.constants.Constants;
import com.company.commonService.LoadingFileData;
import com.company.commonService.UserRole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDataBase implements UserDataManager {
    private List<User> userList;
    private LoadingFileData loadingFileData;
    private String userPath;

    public UserDataBase(){
        if (userList == null){
            userList = new ArrayList<>();
        }
        init();
    }

    public void init(){
        try {
            loadingFileData = new LoadingFileData();
            userPath = loadingFileData.getFilePath().get(Constants.USERS_PATH);
            importUsers();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String addNewUser(User user, UserRole userRole){
        user.setUserRole(userRole);
        userList.add(user);
        try {
            exportUsers();
        } catch (IOException e) {
            e.printStackTrace();
            return Constants.FAILED;
        }
        return Constants.SUCCESSFUL;
    }

    public void exportUsers() throws IOException {
       FileWriter fileWriter = new FileWriter(userPath);
        int order = 1;
        for (User user: userList) {

            fileWriter.write(order++ + ":" + user.getId() + ":" + user.getName() + ":" + user.getSurname()
                    + ":" + user.getAge() + ":" + user.getLogin() + ":" + user.getPassword() + ":" + user.getUserRole().toString() + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void importUsers() throws FileNotFoundException {
        File file = new File(userPath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){

            String user = scanner.nextLine();
            String [] usersArr = user.split(":");

            User newUser = new User(usersArr[Constants.USER_ID],usersArr[Constants.USER_NAME],usersArr[Constants.USER_SURNAME],
                    usersArr[Constants.USER_AGE], usersArr[Constants.USER_LOGIN], usersArr[Constants.USER_PASSWORD]);
            if (usersArr[Constants.USES_ROLE].equals(UserRole.USER.toString()) && !usersArr[Constants.USES_ROLE].isEmpty()){
                newUser.setUserRole(UserRole.USER);
            } else if (usersArr[Constants.USES_ROLE].equals(UserRole.ADMIN.toString()) && !usersArr[Constants.USES_ROLE].isEmpty()){
                newUser.setUserRole(UserRole.ADMIN);
            }

            userList.add(newUser);
        }
    }

    public List<User> getUsers() {
        return userList;
    }

    public User checkForUser(String login , String password){
        for (User user: userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
