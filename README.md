## Solution to 'Notification Test '
This is my solution to this problem or exercise proposed by a company, where we have to make a notification system.

This is the statement:

We have to create a notification system that has the ability to receive a message and depending on
the category and subscribers, notify these users in the channels they are registered.

It will be 3 message categories:

* Sports
* Finance
* Movies

And there will be 3 types of notifications, each type should have its own class to manage the logic of
sending the message independently.

* SMS
* E-Mail
* Push Notification
* 
No notification will actually be sent or the need to communicate with any external APIs, only will
register the send notification in an archive of Logs or in a database.

In the log, it will need to save all the information necessary to identify that the notification has been
sent correctly to the respective subscriber, such as the type of message, type of notification, user
data, time, etc.

No user administration is required, you can use a Mock of users in the source code, and they must have
the following information:

* ID
* Name
* Email
* Phone number
* Subscribed [ ] here you need to list all the categories where the user is subscribed
* Channels [ ] a list of the notification's channels (SMS | E-Mail | Push Notification)
* 
As user interface you need to display 2 main elements.

1. Submission form. A simple form to send the message, which will have 2 fields:

   * Category. List of available categories.
   * Message. Text area, only validate that the message is not empty.
2. Log history. A list of all records in the log, sorted from newest to oldest.

### Tools, Design patterns, Database and architecture used
* java 8
* intellij IDEA
* persistence in txt (database)
* Singleton pattern
* Observable pattern
* MVC

### How I thought the solution

Well first of all I make an analysis of the problem and its possible solution and the patters that I could use or implement; Second I thought in the needed classes and the packages; Finally I make an uml diagram in a book where I used the following classes in the respective packages:

1. model packages
   * Category (class to simulate a newspaper or website where the users will register. here I extend from Observable)
   * Category type (it is the category's topic)
   * Channel (class to simulate the via where the user want to be notified when the category changes its state or publish something new )
   * NotificationSystem (class where are the list of categories, logs and users and some logic to add logs, persist and get from persist file)
   * User (class to simulate a user)
2. service package
   * INotification (interface with one method to notify)
   * EmailServiceImp (class that implement the INotification and use its method to simulate notify via email)
   * PushNotificationServiceImp (class that implement the INotification and use its method to simulate notify via push notification)
   * SmsServiceImp (class that implement the INotification and use its method to simulate notify via sms)
   * Persistence (class where we save the logs in a txt file and read it)
3. view
   * userInterface (two classes to make the simple user interface and manage it)
4. controller
   * ModelFactoryController (class where implement a singleton patter to communicate with one unique instance with the model and service)
   * UserInterfaceController (class where I communicate with the modelFactoryController to get and send information to the model and service. here I implement observer)
5. data
   * logs.txt (file where we save and get the information, it's like a database)

The solution is simple, using the patters observable and singleton, we have an interface where we will send the new message and the category that send the message. Then the controller will communicate with the model and service to notify all the subscribers and save the logs also return the interface a history of the logs.



