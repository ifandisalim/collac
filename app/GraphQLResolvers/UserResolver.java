package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import models.Credential;

import models.Event;
import models.User;
import services.EventService;

import javax.inject.Inject;
import java.util.List;


public class UserResolver implements GraphQLResolver<User> {

    private final EventService eventService;

    @Inject
    public UserResolver(EventService eventService) {
        this.eventService = eventService;
    }


    public List<Event> eventsOwned(User user) {
        return eventService.getEventsOwnedByUser(user.getId());
    }

}
