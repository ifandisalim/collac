package GraphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import models.Credential;

import models.Event;
import models.User;
import services.EventService;
import services.FriendshipService;

import javax.inject.Inject;
import java.util.List;


public class UserResolver implements GraphQLResolver<User> {

    private final FriendshipService friendshipService;

    @Inject
    public UserResolver(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }


    public List<User> friends(User user) {
        return friendshipService.getAllFriends(user.getId());
    }

}
