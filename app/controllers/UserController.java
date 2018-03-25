package controllers;

import actions.TestActionAnnotation;
import com.fasterxml.jackson.databind.JsonNode;
import configurations.Constants;
import errors.Error;
import errors.ErrorFactory;
import errors.JsonError;
import exceptions.DBException;
import exceptions.NotFoundException;
import helpers.FormValidationHelper;
import models.User;
import notification.NewTaskNotification;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;

@TestActionAnnotation
public class UserController extends Controller {

    private final FormFactory formFactory;
    private final UserService userService;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public UserController(FormFactory formFactory, UserService userService, FormValidationHelper formValidationHelper) {
        this.formFactory = formFactory;
        this.userService = userService;
        this.formValidationHelper = formValidationHelper;
    }

    /**
     * Endpoint to create new user.
     * @return UserId of newly created user.
     */
    public Result create() {

        // Validate if input has error. Return Error object is validation error.
        Form<User> userRegistrationForm = formFactory.form(User.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(userRegistrationForm);
        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, Constants.VALIDATION_ERROR, errors));
        }

        // Create new user on DB if no validation error and return the id.
        User newUser = userRegistrationForm.get();
        Integer createdUserId;

        try {
            createdUserId = userService.create(newUser);
        }
        catch (DBException e) {
            return ErrorFactory.create(Http.Status.BAD_REQUEST, e.getMessage());
        }

        return created(Json.toJson(createdUserId));
    }

    /**
     * Endpoint to get the details of a user
     * @param userId ID of user to retrieve
     * @return User object
     */
    public Result get(Integer userId) {

        System.out.println("inside get user");
        try {
            User retrievedUser = userService.get(userId);
            System.out.println("inside get user - returning result");
            ctx().args.put("notificationStrategy", new NewTaskNotification());

            return ok(Json.toJson(retrievedUser));
        } catch (NotFoundException e) {
            return ErrorFactory.create(new Error(Http.Status.NOT_FOUND, Constants.USER_DOESNT_EXIST_ERROR));
        }
    }
}
