package model;

import controller.UserInterfaceController;
import service.Persistence;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class NotificationSystem {

    private Stack<String> logs;
    private List<Category> categories;
    private List<User> users;


    public NotificationSystem(){
        logs = new Stack<String>();
        categories = new ArrayList<>();
        users = new ArrayList<>();
        createData();
    }

    public void createData(){
        Category categorySport = new Category(CategoryType.SPORT);
        Category categoryMovie = new Category(CategoryType.MOVIE);
        Category categoryFinance = new Category(CategoryType.FINANCE);
        User user1 = new User(1L,"peter","peter@gmail.com","3209567294", Arrays.asList(categorySport,categoryFinance),Arrays.asList(Channel.EMAIL,Channel.PUSH_NOTIFICATION));
        User user2 = new User(2L,"ronald","ronald@gmail.com","3209567294", Arrays.asList(categoryMovie),Arrays.asList(Channel.EMAIL));
        User user3 = new User(3L,"maria","maria@gmail.com","3209567294", Arrays.asList(categorySport,categoryFinance,categoryMovie),Arrays.asList(Channel.SMS));
        User user4 = new User(4L,"alicent","alicent@gmail.com","3209567294", Arrays.asList(categorySport),Arrays.asList(Channel.EMAIL,Channel.PUSH_NOTIFICATION));
        User user5 = new User(5L,"john","john@gmail.com","3209567294", Arrays.asList(categoryFinance),Arrays.asList(Channel.SMS));
        User user6 = new User(6L,"arya","arya@gmail.com","3209567294", Arrays.asList(categoryFinance,categoryMovie),Arrays.asList(Channel.EMAIL,Channel.PUSH_NOTIFICATION,Channel.SMS));
        categories.addAll(Arrays.asList(categoryFinance,categoryMovie,categorySport));
        users.addAll(Arrays.asList(user1,user2,user3,user4,user5,user6));
        try {
            persistLogs();
        } catch (IOException e) {
            throw new RuntimeException("error al persistir los logs | "+e.getMessage());
        }
    }

    //Methods to manage the logs
    public void addLog(String log){
        logs.push(log);
    }
    public void removeLog(String log){
       logs.remove(log);
    }
    public boolean isEmptyLogs(){
        return logs.isEmpty();
    }
    //Getters and Setters
    public String getLogs() {
        Stack<String> temp = new Stack<>();
        temp.addAll(logs);
        String content="";
        while (!temp.isEmpty()){
            content+=temp.pop()+"\n";
        }
        return content;
     }
    public void newMessage(String message, String category) throws IOException {
        for (Category c:categories ) {
            if(c.getCategoryType().name().equalsIgnoreCase(category)){
                c.notify(message);
                break;
            }
        }
    }

    /**
     * make the relationship observer-observable
     * @param userInterfaceController
     */
    public void addObserver(UserInterfaceController userInterfaceController){
        for (Category category:categories ) {
            category.addObserver(userInterfaceController);
        }
    }
    public void persistLogs() throws IOException {
        Stack<String> temp = new Stack<>();
        temp.addAll(logs);
        service.Persistence.saveLogs(temp);
     }
    public void getLogsFromPersistence() throws IOException {
        logs.removeAll(logs);
        logs = service.Persistence.getLogs();
    }
    public List<String> getCategoriesNames(){
        return categories.stream().map(category -> {
            return category.getCategoryType().name();
        }).collect(Collectors.toList());
    }
    public void setLogs(Stack<String> logs) {
        this.logs = logs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
