package com.company.panels.adminPanel;

import com.company.entities.Book;
import com.company.constants.Constants;
import com.company.entities.Users;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private Book book;
    private AdminService adminImpl;
    private List<Book> bookList;
    private boolean exitMenu;
    public AdminMenu(Users user) {
        adminImpl = new AdminImpl();
        init(); // showMenu
    }

    public void init(){
        while (true){
            System.out.println("Добро пожаловать в админку");
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "1.Добавить"); // Цыфры с переди
            System.out.println("2.Удалить");
            System.out.println("3.Изменить");
            System.out.println("4.Искать");
            System.out.println("5.Искать по жанрам");
            System.out.println("6.Все книги");
            System.out.println("7.Самые продаваемые книги");
            System.out.println("8.Самые не продаваемые книги");
            System.out.println("9.Создать новую полку" + "\n");
            System.out.println("Пожалуйста выберите функцию:");
            boolean check = true;
            while (check){
                try{
                    int menuNumber = scanner.nextInt();
                    switch (menuNumber){
                        case 1:
                            System.out.println(menuNumber + "." + "Добавить книгу");
                            addBook();
                            check = false;
                            break;
                        case 2:
                            System.out.println(menuNumber + "." + "Удалить книгу");
                            removeBook();
                            check = false;
                            break;
                        case 3:
                            System.out.println("3.Изменить");
                            changeDetails();
                            check = false;
                            break;
                        case 4:
                            System.out.println("4.Искать книгу");
                            searchBook();
                            check = false;
                            break;
                        case 5:
                            System.out.println("5.Искать книгу по жанрам");
                            searchByGenre();
                            check = false;
                            break;
                        case 6:
                            System.out.println(menuNumber + "." + "Все книги");
                            printAllBooks();
                            check = false;
                            break;
                        case 7:
                            System.out.println("7.Самые продаваемые");
                            printMostSold();
                            check = false;
                            break;
                        default:
                            System.out.println("Не правильный номер. Попробуйте еще раз");
                    }
                }catch (Exception e){
                    scanner.nextLine();
                    System.out.println("Выведите число. Попробуйте еще раз: ");
                }
            }
        }
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        boolean checkAddMenu = true;
        while (checkAddMenu){

            System.out.println("Название:" + "       | Главное меню *");
            String name = scanner.nextLine();
            if (name.equals("*"))
                break;

            System.out.println("Автор:" + "       | Главное меню *");
            String author = scanner.nextLine();
            if (author.equals("*"))
                break;

            System.out.println("Жанр:" + "       | Главное меню *");
            String genre = scanner.nextLine();
            if (genre.equals("*"))
                break;

            System.out.println("Цена:" + "       | Главное меню *");

            String price = numCheck();
            if (price.equals("*"))
                break;

            System.out.println("Количество:" + "       | Главное меню *");
            String amount = numCheck();
            if (amount.equals("*"))
                break;

            book = adminImpl.addBook(name, author, genre, Integer.parseInt(price), Integer.parseInt(amount));

            System.out.println(book != null ? ("Успешно добавлена в базу! \n" + book +"\n") : ("Не удалось добавить книгу \n"));

            exitMenu = true;
            while (exitMenu){
               System.out.println("Добавить книгу 1 / Главное меню * ");
               switch (scanner.nextLine()){
                   case "*":
                       checkAddMenu = false;
                       exitMenu = false;
                       break;
                   case "1":
                       exitMenu = false;
                       break;
                   default:
                       System.out.println("Не правильно ввели");
               }
           }
        }
    }

    public String numCheck(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String number = scanner.nextLine();

            if (number.equals("*")){
                return number;
            } else {
                boolean loopBreaker = false;
                int [] nums = {0,1,2,3,4,5,6,7,8,9};
                for (int i = 0; i < number.length(); i++) {
                     loopBreaker = false;
                    for (int j = 0; j < nums.length; j++) {
                        if ((number.charAt(i) + "").equals(String.valueOf(nums[j]))){
                            break;
                        } else if (nums.length-1 == j){
                            loopBreaker = true;
                        }
                    }
                    if (loopBreaker){
                        break;
                    }
                }
                if (loopBreaker){
                    System.out.println("Только цыфры. Попробуйте сного");
                } else {
                    return number;
                }
            }
        }
    }

    public void printAllBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of our all books: ");
        bookList = adminImpl.printAllBooks();
        for (Book books : bookList) {
            System.out.println(books);
        }
        exitMenu = true;
        while (exitMenu){
            System.out.println("\n" + "Главное меню *");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }

    public void removeBook() {
        bookList = adminImpl.printAllBooks();
        for (Book book: bookList) {
            System.out.println(book);
        }
        System.out.println("\nПожалуйста отправьте ID книги которую хотите удалить из базы: "  + "  / Главное меню *");
        Scanner scanner = new Scanner(System.in);
        String bookId = scanner.nextLine();
        if (!(bookId.equals("*"))){

            adminImpl.removeBook(Integer.parseInt(bookId));
            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + "Главное меню *");
                switch (scanner.nextLine()) {
                    case "*":
                        exitMenu = false;
                        break;
                    default:
                        System.out.println("Не правильно ввели");
                }
            }
        }
    }

    public void changeDetails() throws IOException {
        bookList = adminImpl.printAllBooks();
        if (bookList != null && bookList.size() != 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                for (Book books : bookList) {
                    System.out.println(books);
                }
                System.out.println("Пожалуйста выберите Id книги которую вы хотите изменить: " + "/ Главное меню *");
                String id = numCheck();

                if (!id.equals("*")){
                    if (adminImpl.checkStatus(Integer.parseInt(id))) {
                        System.out.println("Нажмите 1 чтобы изменить автора или 2 чтобы изменить название книги: ");
                        int num = scanner.nextInt();
                        switch (num) {
                            case 1:
                                System.out.println("Пожалуйста введите нового автора: ");
                                scanner.nextLine();
                                String author = scanner.nextLine();
                                book = adminImpl.changeDetails(Constants.AUTHOR, Integer.parseInt(id), author);
                                System.out.println(book != null ? "Новый результат: " + book : "Мы не смогли сделать изменение");
                                break;
                            case 2:
                                System.out.println("Пожалуйста введите новое название: ");
                                scanner.nextLine();
                                String name = scanner.nextLine();
                                book = adminImpl.changeDetails(Constants.NAME, Integer.parseInt(id), name);
                                System.out.println(book != null ? "Новый результат: " + book : "Мы не смогли сделать изменение");
                                break;
                            default:
                                System.out.println("Неправильный ввели. Попробуйте еще раз");
                                break;
                        }
                    } else {
                        System.out.println("Извините у нас сейчас нет книг");
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void searchBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название или ключивое слово для поиска книги ? " + " / Главное меню *");
        String keyword = scanner.nextLine();
        if (!keyword.equals("*")){
          bookList = adminImpl.searchBook(keyword);
           if (bookList.size() != 0 && bookList != null){
               for (Book book: bookList) {
                   System.out.println(book);
               }
           }
            exitMenu = true;
            while (exitMenu){
                System.out.println("\n" + "Главное меню *");
                switch (scanner.nextLine()){
                    case "*":
                        exitMenu = false;
                        break;
                    default:
                        System.out.println("Не правильно ввели");
                }
            }
        }
    }

    public void searchByGenre(){
        Scanner scanner = new Scanner(System.in);
        String [] genreType = {"Детектив ","Фантастика","Приключения","Роман"};
        System.out.print("Пожалуйста напишите какой жанр вас интересует " + "(");
        for (int i = 0; i < genreType.length; i++) {
            System.out.print(genreType[i]);
            if (genreType.length-1 != i){
                System.out.print(", ");
            } else{
                System.out.print(")" + ":");
            }
        }
        System.out.println();
        String genre = scanner.nextLine();

        List<Book> bookGenre = adminImpl.searchByGenre(genre);
        if (bookGenre != null){
            bookList(bookGenre);
        } else {
            System.out.println("По запросу " + genre + "ничего не найдено. ");
        }
        exitMenu = true;
        while (exitMenu){
            System.out.println("\n" + "Главное меню *");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }
    public void bookList(List<Book> bookList){
        for (Book book: bookList) {
            System.out.println(book);
        }
    }

    public void printMostSold() {
        Scanner scanner = new Scanner(System.in);
        adminImpl.printMostSold();
        exitMenu = true;
        System.out.println();
        while (exitMenu){
            System.out.println("Главное меню * ");
            switch (scanner.nextLine()){
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }

    public void addDiscount(){
        adminImpl.addDiscount();
    }

    public void createShelve(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название новой полка");
        String shelveType = scanner.nextLine();

        adminImpl.createShelve(shelveType);
    }

    public void buyBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Название книги которую вы хотите купить: ");
        String bookName = scanner.nextLine();
        List<Book> searchResult = adminImpl.searchBook(bookName);


        if (searchResult != null && searchResult.size() != 0) {
            System.out.println("Резултаты поиска: ");
            bookList(searchResult);

            while (true) {
                System.out.println("Пожалуйста введите ID книги которую вы хотите купить: ");
                String bookId = scanner.nextLine();
                if (Integer.parseInt(bookId) > searchResult.get(searchResult.size() - 1).getId() || Integer.parseInt(bookId) < 1) {
                    System.out.println("Вы неправильно ввели ID книги! Попробуйте еще раз пожалуйста");
                } else {
                    System.out.println("Как вас зовут? ");
                    String userName = scanner.nextLine();
                    try {
                        adminImpl.saleBook(Integer.parseInt(bookId), userName, searchResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } else {
            System.out.println("Извините мы не нашли ничего под вашим запросом!");
        }
    }
}
