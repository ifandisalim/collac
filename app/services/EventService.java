package services;

import configurations.Constants;
import errors.Error;
import errors.ErrorFactory;
import exceptions.DBException;
import exceptions.NotFoundException;
import exceptions.PastDateException;
import helpers.DateHelper;
import models.Event;
import models.Task;
import models.User;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;


import javax.inject.Inject;
import java.sql.BatchUpdateException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static play.mvc.Results.ok;

public class EventService {

    private final TaskService taskService;
    private final DateHelper dateHelper;

    @Inject
    public EventService(TaskService taskService, DateHelper dateHelper) {
        this.taskService = taskService;
        this.dateHelper = dateHelper;
    }

    public Integer create(Event newEvent) throws PastDateException, ParseException {
        checkDueDateIsNotPast(newEvent.getDue());
        newEvent.save();
        return newEvent.getId();
    }

    private void checkDueDateIsNotPast(String dueDate) throws PastDateException, ParseException {
            Date date = dateHelper.convertStringToDateTime(dueDate);
            if(date.getTime() < new Date().getTime()) {
                throw new PastDateException();
            }
    }


    public Event get(Integer eventId) throws NotFoundException {
        Event retrievedEvent = Event.find.byId(eventId);
        if(retrievedEvent == null) {
            throw new NotFoundException(eventId);
        }
        return retrievedEvent;
    }


    public Integer addMembers(Integer eventId, List<User> users) throws NotFoundException, DBException {
        Event retrievedEvent = Event.find.byId(eventId);

        if(retrievedEvent == null) {
           throw new NotFoundException(eventId);
        }

        List<User> currentMembers = retrievedEvent.getMembers();
        currentMembers.addAll(users);

        try {
            retrievedEvent.update();
        }
        catch(RuntimeException e){
            throw new DBException(e.getCause().getMessage());
        }

        return retrievedEvent.getId();
    }


    public Integer addTask(Integer eventId, Task newTask) throws NotFoundException{

        Event retrievedEvent = Event.find.byId(eventId);
        if(retrievedEvent == null) {
            throw new NotFoundException(eventId);
        }

        User retrievedCreator = User.find.byId(newTask.getCreator().getId());
        if(retrievedCreator == null) {
            throw new NotFoundException(newTask.getCreator().getId());
        }

        taskService.create(eventId, newTask);
        return retrievedEvent.getId();
    }


    public List<Event> getEventsOwnedByUser(Integer userId) {
        return Event.find.query().where().eq("owner.id", userId)
                .findList();
    }

}
