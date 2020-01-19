package seventythree.dawgpack.server;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seventythree.dawgpack.model.Group;
import seventythree.dawgpack.model.User;

import java.util.List;

@RestController
public class DawgPackController {

    // OK ResponseEntity
    private static final ResponseEntity OK = ResponseEntity.ok(HttpStatus.OK);

    // post user endpoint
    @PostMapping("/api/postuser")
    public ResponseEntity postUser(@RequestBody User u) {
        UserManager.getInstance().addUser(u);
        System.out.println("received post user request with user " + u.getUsername());
        return OK;
    }

    // post group endpoint
    // group deserialization only works with GSON for some reason
    @PostMapping("/api/postgroup")
    public ResponseEntity postGroup(@RequestBody String json) {
        // System.out.println("received json: " + json);
        Group g = new Gson().fromJson(json, Group.class);
        // add this group to the manager
        GroupManager.getInstance().addGroup(g);
        System.out.println("received post group request with group id " + g.getId());
        return OK;
    }

    // join group endpoint
    @RequestMapping("/api/joingroup")
    public ResponseEntity joinGroup(@RequestParam String username, @RequestParam String groupId) {
        // find the user
        UserManager userManager = UserManager.getInstance();
        User user = userManager.getUser(username);

        // if not found, complain
        if (user == null) {
            System.err.println("user not found: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // find the group
        GroupManager groupManager = GroupManager.getInstance();
        Group group = groupManager.getGroup(groupId);

        // if not found, complain
        if (group == null) {
            System.err.println("group not found: " + groupId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // add the user to the group
        group.addUser(user);
        System.out.println("added user " + username + " to group id " + groupId);

        // return success
        return OK;
    }

    // get group endpoint
    @RequestMapping("/api/getgroup")
    public Group getGroup(@RequestParam String groupId) {
        Group g = GroupManager.getInstance().getGroup(groupId);
        return g;
    }

    // get groups
    @RequestMapping("/api/getgrouplist")
    public List<Group> getGroupList() {
        return GroupManager.getInstance().getGroups();
    }

    // leave group endpoint
    @RequestMapping("/api/leavegroup")
    public ResponseEntity leaveGroup(@RequestParam String username, @RequestParam String groupId) {
        // find the user
        UserManager userManager = UserManager.getInstance();
        User user = userManager.getUser(username);

        // if not found, complain
        if (user == null) {
            System.err.println("user not found: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // find the group
        GroupManager groupManager = GroupManager.getInstance();
        Group group = groupManager.getGroup(groupId);

        // if not found, complain
        if (group == null) {
            System.err.println("group not found: " + groupId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // remove the user from the group
        group.removeUser(user);

        System.out.println("removed user " + username + " from group " + groupId);

        // return success
        return OK;
    }
}
