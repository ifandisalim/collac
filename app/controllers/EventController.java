package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import configurations.Constants;
import errors.Error;
import errors.ErrorFactory;
import errors.JsonError;
import exceptions.DBException;
import exceptions.NotFoundException;
import exceptions.PastDateException;
import helpers.DateHelper;
import helpers.FormValidationHelper;
import models.Event;
import models.Task;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.EventService;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class EventController {

    private final FormFactory formFactory;
    private final EventService eventService;
    private final FormValidationHelper formValidationHelper;

    @Inject
    public EventController(FormFactory formFactory, EventService eventService, FormValidationHelper formValidationHelper) {
        this.formFactory = formFactory;
        this.eventService = eventService;
        this.formValidationHelper = formValidationHelper;
    }

    /**
     * Endpoint to create new event.
     * @return eventId of newly created event.
     */
    public Result create() {

        // Validate if input has error. Return Error object is validation error.
        Form<Event> eventCreationForm = formFactory.form(Event.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(eventCreationForm);
        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, Constants.VALIDATION_ERROR, errors));
        }

        // Create new user on DB if no validation error and return the id.
        Event newEvent = eventCreationForm.get();
        Integer newEventId;
        try {
            newEventId = eventService.create(newEvent);

        } catch (PastDateException | ParseException e) {
            return ErrorFactory.create(Http.Status.BAD_REQUEST, e.getMessage());

        }

        return created(Json.toJson(newEventId));
    }


    public Result get(Integer eventId) {
        try {
            Event retrievedEvent = eventService.get(eventId);
            return ok(Json.toJson(retrievedEvent));

        } catch (NotFoundException e) {
            return ErrorFactory.create(new Error(Http.Status.BAD_REQUEST, e.getMessage()));
        }
    }


    public Result addMembers(Integer eventId) {
        JsonNode requestNode = request().body().asJson().get("newMembers");
        String newMembersListString =Json.stringify(requestNode);

        List<User> newMembers;
        Integer updatedEventId;

        try {
            newMembers = new ObjectMapper().readValue(newMembersListString, new TypeReference<List<User>>(){});
            updatedEventId = eventService.addMembers(eventId, newMembers);
        }
        catch (IOException | NotFoundException | DBException e) {
            return ErrorFactory.create(Http.Status.BAD_REQUEST, e.getMessage());
        }

        return created(Json.toJson(updatedEventId));
    }

    public Result addTask(Integer eventId) {

        // Validate if input has error. Return Error object is validation error.
        Form<Task> taskCreationForm = formFactory.form(Task.class).bindFromRequest();
        JsonNode errors = formValidationHelper.validateFormErrors(taskCreationForm);
        if(errors != null) {
            return ErrorFactory.create(new JsonError(Http.Status.BAD_REQUEST, Constants.VALIDATION_ERROR, errors));
        }

        Task task = taskCreationForm.get();
        task.setDone(false);

        try {
            return ok(Json.toJson(eventService.addTask(eventId, task)));
        }
        catch (NotFoundException e) {
            return ErrorFactory.create(new Error(Http.Status.BAD_REQUEST, e.getMessage()));
        }
    }


}
