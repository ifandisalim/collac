package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Table(name = "task")
@Entity(name = "task")
public class Task extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assigned_id")
    private User assigned;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Constraints.Required
    private String title;

    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    public static Finder<Integer, Task> find = new Finder<>(Task.class);

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", isDone=" + isDone +
                ", event=" + event +
                '}';
    }
}
