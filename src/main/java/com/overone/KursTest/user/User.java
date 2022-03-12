package com.overone.KursTest.user;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String surname;
    private String password;
    private Roles role;
    private int money;

    public User(){}
    public User(String name, String surname, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        switch (role) {
            case "1":
                this.role = Roles.USER;
                break;
            case "2":
                this.role = Roles.SALER;
                break;
            case "3":
                this.role = Roles.ADMIN;
                break;
            case "4":
                this.role = Roles.COOK;
                break;
            default:
                this.role = Roles.USER;
        }
        this.money = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User " +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", money=" + money;
    }
}
