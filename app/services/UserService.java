package services;


import exceptions.DBException;
import exceptions.NotFoundException;
import models.Event;
import models.User;

import java.util.List;


public class UserService {

    public Integer create(User newUser) throws DBException {
        try{
            newUser.save();
        } catch (RuntimeException e) {
            throw new DBException(e.getCause().getMessage());
        }

        return newUser.getId();
    }

    public User get(Integer userId) throws NotFoundException{
        User retrievedUser = User.find.byId(userId);

        if(retrievedUser == null) {
           throw new NotFoundException(userId);
        }

        return retrievedUser;
    }

}
