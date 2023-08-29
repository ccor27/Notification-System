package controller;

import model.CategoryType;
import model.Channel;
import model.NotificationSystem;
import model.User;
import service.EmailServiceImp;
import service.INotification;
import service.PushNotificationServiceImp;
import service.SmsServiceImp;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController {

    private NotificationSystem notificationSystem;
    private INotification smsNotification;
    private INotification emailNotification;
    private INotification pushNotification;
    private String log;
    private static class SingletonHolder{
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance(){
        return SingletonHolder.eINSTANCE;
    }
    public ModelFactoryController(){
        notificationSystem=new NotificationSystem();
        smsNotification = new SmsServiceImp();
        emailNotification = new EmailServiceImp();
        pushNotification = new PushNotificationServiceImp();
        log="";
    }
    public void addObserver(UserInterfaceController userInterfaceController){
        notificationSystem.addObserver(userInterfaceController);
    }
    public List<String> categoriesNames(){
        return notificationSystem.getCategoriesNames();
    }
    public void newMessage (String categoryName, String message) throws IOException {
        notificationSystem.newMessage(message,categoryName);
    }
    public String getLogs() throws IOException {
        notificationSystem.getLogsFromPersistence();
        return notificationSystem.getLogs();
    }
    public List<User> getUsers(){
        return notificationSystem.getUsers();
    }

    /**
     * This method is called in the UserInterfaceController to send the message by the correct channel
     * and persist the logs in the txt file
     * @param message
     * @param userData
     * @param categoryType
     * @param channels
     */
    public void sendNotification(String message, String userData, CategoryType categoryType, List<Channel> channels) throws IOException {
        for (Channel channel:channels ) {
            switch (channel){
                case SMS:
                    log =smsNotification.sendNotification(message,userData,categoryType);
                    notificationSystem.addLog(log);
                    break;
                case EMAIL:
                    log =  emailNotification.sendNotification(message,userData,categoryType);
                    notificationSystem.addLog(log);
                    break;
                case PUSH_NOTIFICATION:
                    log = pushNotification.sendNotification(message,userData,categoryType);
                    notificationSystem.addLog(log);
                    break;
            }
            log="";
        }
        notificationSystem.persistLogs();
    }
}
