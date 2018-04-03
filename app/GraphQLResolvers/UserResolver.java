package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import exceptions.DBException;
import models.Credential;

import models.Event;
import models.Task;
import models.User;
import services.EventService;
import services.FriendshipService;
import services.TaskService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class UserResolver implements GraphQLResolver<User> {

    private final FriendshipService friendshipService;
    private final TaskService taskService;

    @Inject
    public UserResolver(FriendshipService friendshipService, TaskService taskService) {
        this.friendshipService = friendshipService;
        this.taskService = taskService;
    }


    public List<User> friends(User user) {
        return friendshipService.getAllFriends(user.getId());
    }

    public List<Task> tasks(User user) {
        List<Task> userTasks = new ArrayList<>();

        try {
            userTasks = taskService.getTasksOfUser(user.getId());
        } catch (DBException e) {
            e.printStackTrace();
        }

        return userTasks;
    }

}
