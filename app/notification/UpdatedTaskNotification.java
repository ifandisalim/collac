package notification;

public class UpdatedTaskNotification implements NotificationStrategy {
    @Override
    public void sendNotification() {
        System.out.println("Task updated");
    }
}
