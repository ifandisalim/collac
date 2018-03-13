package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import models.Event;
import models.User;

import java.util.List;

public class RootQueryResolver implements GraphQLQueryResolver {

    public RootQueryResolver() {
    }

    public List<User> users() {
        return User.find.all();
    }
    public List<Event> events() { return Event.find.all(); }
}
