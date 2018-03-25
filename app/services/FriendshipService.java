package services;

import exceptions.DBException;
import exceptions.NotFoundException;
import helpers.DateHelper;
import models.Friendship;
import models.User;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FriendshipService {

    private final DateHelper dateHelper;

    @Inject
    public FriendshipService(DateHelper dateHelper) {
        this.dateHelper = dateHelper;
    }

    public Integer create(Friendship friendship) throws DBException, NotFoundException {
        try{

            User requester = User.find.byId(friendship.getFriendRequester().getId());
            User accepter = User.find.byId(friendship.getFriendAccepter().getId());

            if(requester == null ) {
                throw new NotFoundException(friendship.getFriendRequester().getId());
            }

            if(accepter == null) {
                throw new NotFoundException(friendship.getFriendAccepter().getId());
            }

            friendship.setDate(dateHelper.convertDateTimeToString(new Date()));
            friendship.save();
        } catch (RuntimeException e) {
            throw new DBException(e.getCause().getMessage());
        }

        return friendship.getId();
    }

    public List<User> getAllFriends(Integer userId) {

        // Get all the friendship thats accepted / requested
        List<Integer> friendsRequestedId =
                Friendship.find.query()
                .where()
                .eq("friend_requester_id", userId)
                .findList()

                .stream()
                .map(friendship -> friendship.getFriendAccepter().getId())
                .collect(Collectors.toList());


        List<Integer> allFriendsId =
                Friendship.find.query()
                .where()
                .eq("friend_accepter_id", userId)
                .findList()

                .stream()
                .map(friendship -> friendship.getFriendRequester().getId())
                .collect(Collectors.toList());

        allFriendsId.addAll(friendsRequestedId);


        return User.find.query().where().in("id", allFriendsId).findList();




    }

}
