package com.overone.KursTest.shop;

public class Item {
    static int nextId = 0;
    public int id;
    public String name;
    public double price;
    public String url = "https://html5book.ru/wp-content/uploads/2015/10/black-dress.jpg";
    public int number;

    public Item(String name, double price, int number,  String url) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.url = url;
        this.id = nextId++;
    }
}
