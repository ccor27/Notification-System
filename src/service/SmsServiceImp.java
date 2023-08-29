package service;

import model.CategoryType;

import java.time.LocalDateTime;

public class SmsServiceImp implements INotification{
    @Override
    public String sendNotification(String message, String userData, CategoryType categoryType) {
        return "LOG: the user: "+userData+" received the message by "+categoryType.name()+" successfully at ["+ LocalDateTime.now()+"] via sms";
    }
}
