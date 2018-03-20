package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import models.Event;
import models.Task;
import models.User;

import java.util.List;

public class RootQueryResolver implements GraphQLQueryResolver {

    public RootQueryResolver() {
    }

    public User user(Integer id) {
        return User.find.byId(id);
    }

    public List<User> users() {
        return User.find.all();
    }


    public Event event(Integer id) {
        return Event.find.byId(id);
    }

    public List<Event> events() { return Event.find.all(); }


    public Task task(Integer id) {
        return Task.find.byId(id);
    }

    public List<Task> tasks() { return Task.find.all(); }
}
