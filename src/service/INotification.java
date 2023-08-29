package service;

import model.CategoryType;
import model.User;

import java.util.Observer;

public interface INotification{
    public String sendNotification(String message, String userData, CategoryType categoryType);
}
