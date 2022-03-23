package com.company;

import com.company.Login.AdminPanel.AdminMenu;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        BookStoreInterface bookStoreInterface = null;
        try {
                bookStoreInterface = new BookStoreImpl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException p ) {
            p.printStackTrace();
        }
       AdminMenu adminMenu = new AdminMenu(bookStoreInterface);

   }

}
