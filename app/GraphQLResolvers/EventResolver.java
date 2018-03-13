package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import models.Event;
import models.User;
import services.EventService;

import javax.inject.Inject;

public class EventResolver implements GraphQLResolver<Event> {

    private final EventService eventService;

    @Inject
    public EventResolver(EventService eventService) {
        this.eventService = eventService;
    }

    public User owner(Event event) {
        return User.find.byId(event.getOwner().getId());
    }

}
