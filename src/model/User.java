package model;

import service.INotification;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class User {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Category> subscribed;
    private List<Channel> notificationChannels;

    public User(){}
    public User(Long id, String name, String email, String phoneNumber, List<Category> subscribed, List<Channel> notificationChannels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subscribed = subscribed;
        this.notificationChannels = notificationChannels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Category> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<Category> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Channel> getNotificationChannels() {
        return notificationChannels;
    }

    public void setNotificationChannels(List<Channel> notificationChannels) {
        this.notificationChannels = notificationChannels;
    }
    @Override
    public String toString() {
        return "[" + name + '\'' +", " + email + '\'' +", "+ phoneNumber + '\'' +']';
    }
}
