package actions;


import notification.NotificationStrategy;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TestAction extends Action.Simple {

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        System.out.println("before delegate call");

        CompletionStage<Result> call = delegate.call(ctx);

        NotificationStrategy notificationStrategy = (NotificationStrategy) ctx.args.get("notificationStrategy");
        notificationStrategy.sendNotification();
        return call;
    }
}
