package com.company.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private String genre;
    private int price;
    private int amount;
    private int amountLeft;
    private Date date;


    public Book(int id, String name, String author, String genre, int price, int amount, int amountLeft, Date date) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.amountLeft =amountLeft;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", amountLeft=" + amountLeft +
                ", date=" + new SimpleDateFormat("dd/MM/YYYY").format(date) +
                '}';
    }
}
