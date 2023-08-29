package view;

import controller.UserInterfaceController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class userInterface extends JFrame{
    private JPanel panel1;
    private JTextField txtMessage;
    private JComboBox comboCategories;
    private JButton btnSendNotification;
    private JTextArea txtAreaLogs;
    private UserInterfaceController userInterfaceController = new UserInterfaceController();
    private String message;
    private String nameCategory;
public userInterface(){
    setContentPane(panel1);
    setTitle("Notification system");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(300,500);
    setLocationRelativeTo(null);
    setVisible(true);

    btnSendNotification.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          message = txtMessage.getText();
          if(message.length()==0 || nameCategory.length()==0){
              //ERROR the filed mustn't be null
              System.out.println("error, the filed mustn't be null");
          }else{
              try {
                  userInterfaceController.newMessage(message,nameCategory);
                  showLogs();
              } catch (IOException ex) {
                  throw new RuntimeException("Error trying to send message: "+ex.getMessage());
              }finally {
                  txtMessage.setText("");
                  comboCategories.setSelectedItem(0);
              }
          }

        }
    });
    setValuesToComboBox();
    comboCategories.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        nameCategory=comboCategories.getSelectedItem().toString();
        }
    });
}
public void showLogs() throws IOException {
    String content = userInterfaceController.getLogs();
    txtAreaLogs.selectAll();
    txtAreaLogs.replaceSelection("");
    txtAreaLogs.setText(content);
}
public void setValuesToComboBox(){
    List<String> categories = userInterfaceController.getCategories();
    this.comboCategories.addItem("");
    for (String category:categories) {
        this.comboCategories.addItem(category);
    }
}

}
