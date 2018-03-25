package notification;

public class NotificationHandler {

    private NotificationStrategy notificationStrategy;

    public NotificationHandler(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void sendNotification() {
        notificationStrategy.sendNotification();
    }
}
