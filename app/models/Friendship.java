package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Friendship extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "friend_requester_id")
    private User friendRequester;

    @ManyToOne
    @JoinColumn(name = "friend_accepter_id")
    private User friendAccepter;

    private Date date;

    public static Finder<Integer, Friendship> find = new Finder<>(Friendship.class);

    public Friendship() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", friendRequester=" + friendRequester +
                ", friendAccepter=" + friendAccepter +
                ", date=" + date +
                '}';
    }
}
