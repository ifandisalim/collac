package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Table(name = "credential")
@Entity(name = "credential")
public class Credential extends Model {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String userName;

    private String password;

    public static Finder<Integer, Credential> find = new Finder<>(Credential.class);

    public Credential() {
    }

    public Credential(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
