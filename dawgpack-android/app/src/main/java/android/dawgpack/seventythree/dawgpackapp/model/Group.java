package android.dawgpack.seventythree.dawgpackapp.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    // the location this group is meeting at
    private StartLocation startLocation;

    // the location this group is going to
    private EndLocation endLocation;

    // the id of this group
    private String id;

    // the users in this group
    private List<User> users;

    // the departure time of this group
    private long departTime;

    // the properties of this group
    // private Properties properties;

    // constructor
    public Group(StartLocation startLocation, EndLocation endLocation, long departTime) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departTime = departTime;
        this.users = new ArrayList<>();
        // this.properties = new Properties();
        this.id = nextId();
    }

    // returns the next id for a group
    public static String nextId() {
        // todo: code goes here
        return "0";
    }

    // adds a user to the group
    public void addUser(User user) {
        this.users.add(user);
    }

    // removes a user from the group
    public void removeUser(User user) {
        this.users.remove(user);
    }

    // id
    public String getId() {
        return id;
    }

    // depart time
    public long getDepartTime() {
        return departTime;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }
}
