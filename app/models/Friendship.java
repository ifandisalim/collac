package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
@Table
public class Friendship extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "friend_requester_id")
    @Constraints.Required
    private User friendRequester;

    @ManyToOne
    @JoinColumn(name = "friend_accepter_id")
    @Constraints.Required
    private User friendAccepter;

    private String date;

    private boolean accepted;

    public static Finder<Integer, Friendship> find = new Finder<>(Friendship.class);

    public Friendship() {
        this.accepted = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFriendRequester() {
        return friendRequester;
    }

    public void setFriendRequester(User friendRequester) {
        this.friendRequester = friendRequester;
    }

    public User getFriendAccepter() {
        return friendAccepter;
    }

    public void setFriendAccepter(User friendAccepter) {
        this.friendAccepter = friendAccepter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", friendRequester=" + friendRequester +
                ", friendAccepter=" + friendAccepter +
                ", date=" + date +
                ", accepted=" + accepted +
                '}';
    }
}
