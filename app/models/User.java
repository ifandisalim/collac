package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name = "user")
@Entity(name = "user")
public class    User extends Model {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Constraints.Required
    private String firstName;

    @Constraints.Required
    private String lastName;

    @Constraints.Required
    @Constraints.Email
    @Column(unique = true)
    private String email;

    private String pushToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    @Constraints.Required
    @JsonIgnore
    private Credential credential;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Event> eventsOwned;

    @ManyToMany
    @JsonIgnore
    private List<Event> eventsJoined;



    public static Finder<Integer, User> find = new Finder<>(User.class);

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<Event> getEventsOwned() {
        return eventsOwned;
    }

    public void setEventsOwned(List<Event> eventsOwned) {
        this.eventsOwned = eventsOwned;
    }

    public List<Event> getEventsJoined() {
        return eventsJoined;
    }

    public void setEventsJoined(List<Event> eventsJoined) {
        this.eventsJoined = eventsJoined;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", pushToken='" + pushToken + '\'' +
                ", credential=" + credential +
                ", eventsOwned=" + eventsOwned +
                ", eventsJoined=" + eventsJoined +
                '}';
    }
}
