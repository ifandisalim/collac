package services;

import errors.Error;
import errors.ErrorFactory;
import models.Credential;
import models.User;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import javax.annotation.Nullable;

import static play.mvc.Results.ok;

public class AuthenticationService {

    @Nullable
    private User getUserByEmail(String email) {
        User user =  User.find.query().where()
                .eq("email", email).findOne();

        if(user == null) {
            System.out.println("Cant find user with email: " + email);
            return null;
        }

        return user;
    }


    public Result authenticateByEmailAndPassword(String email, String password) {
        User user = getUserByEmail(email);

        if(user == null) {
            return ErrorFactory.create(new Error(Http.Status.NOT_FOUND, "Cant find user with email: " + email));
        }

        if(!password.equals(user.getCredential().getPassword())) {
            return ErrorFactory.create(new Error(Http.Status.UNAUTHORIZED, "Wrong password"));
        }
        return ok(Json.toJson(user));
    }
}
