package com.company.DataBase;

import com.company.Constants;
import com.company.LoadingFileData;
import com.company.Login.UserTypes;
import com.company.Login.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserDataBase {
    private List<Users> users;
    private LoadingFileData loadingFileData;
    private HashMap<String, Users> usersList;
    private String userPath;

    public UserDataBase(){
        users = new ArrayList<>();
        usersList = new HashMap<>();
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
    public String addNewUser(Users user, UserTypes userType){
        users.add(user);
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
        for (int i = 0; i < users.size(); i++) {
            fileWriter.write(i+1 + ":" + users.get(i).getId() + ":" +
                    users.get(i).getName() + ":" + users.get(i).getSurname() + ":" + users.get(i).getAge() + "\n");
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
            users.add(new Users(usersArr[Constants.USER_ID],usersArr[Constants.USER_NAME],usersArr[Constants.USER_SURNAME],
                    usersArr[Constants.USER_AGE]));
        }
    }

    public List<Users> getUsers() {
        return users;
    }


}
