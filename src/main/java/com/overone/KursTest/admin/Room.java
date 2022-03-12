package com.overone.KursTest.admin;

import com.overone.KursTest.user.User;

public class Room {
    private int number;
    private Comfort comfort;
    private boolean isBooked;
    private int days;
    private User currentUser;

    public Room(){}
    public Room(int number, Comfort comfort, boolean isBooked,int days,  User currentUser) {
        this.number = number;
        this.comfort = comfort;
        this.isBooked = isBooked;
        this.days = days;
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Room{" +
                ", number=" + number +
                ", comfort=" + comfort +
                ", isBooked=" + isBooked +
                ", days=" + days +
                ", currentUser=" + currentUser +
                '}';
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Comfort getComfort() {
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

