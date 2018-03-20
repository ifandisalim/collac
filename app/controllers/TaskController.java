package controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import errors.ErrorFactory;
import exceptions.DBException;
import exceptions.NotFoundException;
import exceptions.RelationshipNotFoundException;
import models.Task;
import models.User;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.TaskService;

import javax.inject.Inject;

import static play.mvc.Controller.request;
import static play.mvc.Results.created;

public class TaskController {

    private final TaskService taskService;

    @Inject
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public Result addAssigned(Integer taskId) {

        // Validate if input has error. Return Error object is validation error.
        JsonNode requestNode = request().body().asJson().get("assigned");
        Integer assignedId = requestNode.asInt();

        try {
            taskService.addAssigned(taskId, assignedId);

        } catch (NotFoundException | RelationshipNotFoundException e) {
            return ErrorFactory.create(Http.Status.BAD_REQUEST, e.getMessage());
        } catch (DBException e) {
            return ErrorFactory.create(Http.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return created();
    }


    public Result updateTask(Integer taskId){
        JsonNode requestNode = request().body().asJson();
        Task updatedTask = Json.fromJson(requestNode, Task.class);
        updatedTask.setId(taskId);

        try{
           taskService.update(updatedTask);
        }
        catch(DBException e) {
            return ErrorFactory.create(Http.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return created();
    }

}
