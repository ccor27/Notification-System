package controller;

import model.Category;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UserInterfaceController implements Observer {

    private ModelFactoryController modelFactoryController;

    public UserInterfaceController(){
        modelFactoryController = ModelFactoryController.getInstance();
        modelFactoryController.addObserver(this);
    }

    public List<String> getCategories(){
        return modelFactoryController.categoriesNames();
    }
    public void newMessage(String message,String category) throws IOException {
        modelFactoryController.newMessage(category,message);
    }
    public String getLogs() throws IOException {
        return modelFactoryController.getLogs();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Category && arg instanceof String) {
            Category category = (Category) o;
            String message = (String) arg;
            modelFactoryController.getUsers().forEach(user -> {
                if (user.getSubscribed().contains(category)) {
                    try {
                        modelFactoryController.sendNotification(message, user.toString(), category.getCategoryType(), user.getNotificationChannels());
                    } catch (IOException e) {
                        throw new RuntimeException("The error is because something is wrong in the save"+e.getMessage());
                    }
                }
            });
        } else {
            System.out.println("error in userInterface line 30");
        }
    }
}
