package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.ErrorFactory;
import errors.JsonError;
import exceptions.DBException;
import exceptions.NotFoundException;
import helpers.FormValidationHelper;
import models.Friendship;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FriendshipService;

import javax.inject.Inject;

public class FriendshipController extends Controller {

    private final FriendshipService friendshipService;
    private final FormFactory formFactory;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public FriendshipController(FriendshipService friendshipService,
                                FormFactory formFactory,
                                FormValidationHelper formValidationHelper)
    {
        this.friendshipService = friendshipService;
        this.formFactory = formFactory;
        this.formValidationHelper = formValidationHelper;
    }

    public Result create() {
        // Validate if input has error. Return Error object is validation error.
        Form<Friendship> friendshipRequestForm = formFactory.form(Friendship.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(friendshipRequestForm);
        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, Constants.VALIDATION_ERROR, errors));
        }

        // Create new user on DB if no validation error and return the id.
        Friendship newFriendship = friendshipRequestForm.get();
        Integer createdFriendshipId;

        try {
            createdFriendshipId = friendshipService.create(newFriendship);
        }
        catch (DBException | NotFoundException e) {
            return ErrorFactory.create(Http.Status.BAD_REQUEST, e.getMessage());
        }


        return created(Json.toJson(createdFriendshipId));

    }
}
