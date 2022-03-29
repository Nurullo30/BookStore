package com.company.entities;

import com.company.constants.Constants;
import com.company.commonService.LoadingFileData;
import com.company.commonService.UserTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDataBase implements UserDataManager {
    private List<Users> usersList;
    private LoadingFileData loadingFileData;
    private String userPath;
    private Users users;

    public UserDataBase(){
        usersList = new ArrayList<>();
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
        users = new Users();
    }
    public String addNewUser(Users user, UserTypes userType){
        usersList.add(user);
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
        for (Users user: usersList) {
            int order = 1;
            fileWriter.write(order++ + ":" + user.getId() + ":" + user.getName() + ":" + user.getSurname()
                    + ":" + user.getAge() + ":" + user.getLogin() + ":" + user.getPassword() + "\n");
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
            usersList.add(new Users(usersArr[Constants.USER_ID],usersArr[Constants.USER_NAME],usersArr[Constants.USER_SURNAME],
                    usersArr[Constants.USER_AGE], usersArr[Constants.USER_LOGIN], usersArr[Constants.USER_PASSWORD]));
        }
    }

    public List<Users> getUsers() {
        return usersList;
    }
}
