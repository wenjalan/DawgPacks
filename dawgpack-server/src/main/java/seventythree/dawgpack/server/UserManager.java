package seventythree.dawgpack.server;

import seventythree.dawgpack.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {

    // singleton instance
    private static UserManager instance = new UserManager();

    // returns the instance
    public static UserManager getInstance() {
        return instance;
    }

    // the list of currently active users
    public Map<String, User> users = new HashMap<>();

    // private constructor
    private UserManager() { }

    // adds a user to the manager
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // removes a user from the manager
    public void removeUser(User user) {
        users.remove(user.getUsername());
    }

    // returns a user given their username
    public User getUser(String username) {
        return users.get(username);
    }

    // returns the list of users
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

}
