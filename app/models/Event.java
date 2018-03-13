package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "event")
@Entity(name = "event")
public class Event extends Model {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String title;

    @Constraints.Required
    private String description;

    @ManyToMany(mappedBy = "eventsJoined")
    @JoinColumn(name = "event_members")
    private List<User> members;

    @OneToMany(mappedBy = "event")
    private List<Task> tasks;

    @Constraints.Required
    private String due;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    @Constraints.Required
    private User owner;

    private String socketIORoom;

    public static Finder<Integer, Event> find = new Finder<>(Event.class);

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getSocketIoRoom() {
        return socketIORoom;
    }

    public void setSocketIoRoom(String socketIORoom) {
        this.socketIORoom = socketIORoom;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", members=" + members +
                ", tasks=" + tasks +
                ", due=" + due +
                ", owner=" + owner +
                ", socketIORoom='" + socketIORoom + '\'' +
                '}';
    }
}
