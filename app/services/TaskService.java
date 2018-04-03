package services;

import exceptions.DBException;
import exceptions.NotFoundException;
import exceptions.RelationshipNotFoundException;
import models.Event;
import models.Task;
import models.User;

import javax.inject.Inject;
import java.util.List;

public class TaskService {

    private final UserService userService;

    @Inject
    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public Task create(Integer eventId, Task newTask) {
        Event event = new Event();
        event.setId(eventId);
        newTask.setEvent(event);

        newTask.save();
        return newTask;
    }

    public Task get(Integer taskId) throws NotFoundException {
        Task retrievedTask = Task.find.byId(taskId);

        if(retrievedTask == null) {
            throw new NotFoundException(taskId);
        }

        return retrievedTask;
    }


    public List<Task> getTasksOfUser(Integer userId) throws DBException  {
        try {
            return Task.find.query()
                    .where()
                    .eq("assigned_id", userId)
                    .findList();

        } catch (RuntimeException e) {
            throw new DBException(e.getCause().getMessage());
        }

    }

    public void update(Task task) throws DBException {

        try{
            task.update();
        } catch (RuntimeException e) {
            throw new DBException(e.getCause().getMessage());
        }

    }


    public void addAssigned(Integer taskId, Integer assignedId) throws NotFoundException, RelationshipNotFoundException, DBException {
        User assigned = this.userService.get(assignedId);
        Task retrievedTask = get(taskId);

        List<User> members = retrievedTask.getEvent().getMembers();
        Boolean isAssignedInEvent = members.stream().anyMatch(user -> assignedId.equals(user.getId()));
        if(!isAssignedInEvent) {
            throw new RelationshipNotFoundException(assignedId, retrievedTask.getEvent().getId());
        }

        retrievedTask.setAssigned(assigned);

        try{
            retrievedTask.update();
        }
        catch (RuntimeException e) {
            throw new DBException(e.getCause().getMessage());
        }

    }

}
