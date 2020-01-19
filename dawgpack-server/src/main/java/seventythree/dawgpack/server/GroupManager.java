package seventythree.dawgpack.server;

import seventythree.dawgpack.model.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// manages all currently active groups
public class GroupManager {

    // singleton instance
    private static GroupManager instance = new GroupManager();

    // returns the singleton instance
    public static GroupManager getInstance() {
        return instance;
    }

    // the list of currently registered groups
    private Map<String, Group> groups = new HashMap<>();

    // private constructor
    private GroupManager() {}

    // adds a group to the manager
    public void addGroup(Group g) {
        groups.put(g.getId(), g);
    }

    // removes a group from the manager
    public void removeGroup(Group g) {
        groups.remove(g.getId());
    }

    // returns a group given their id
    public Group getGroup(String id) {
        return groups.get(id);
    }

    // returns a list of all groups currently managed
    public List<Group> getGroups() {
        return new ArrayList<>(groups.values());
    }

}
