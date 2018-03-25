package notification;

public class NewTaskNotification implements NotificationStrategy {
    @Override
    public void sendNotification() {
        System.out.println("New task created");
    }
}
