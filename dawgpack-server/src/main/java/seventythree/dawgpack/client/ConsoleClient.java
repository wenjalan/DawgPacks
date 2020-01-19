package seventythree.dawgpack.client;

import com.google.gson.Gson;
import okhttp3.*;
import seventythree.dawgpack.model.*;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.List;

// test console client
public class ConsoleClient {

    // server URL
    public static final String SERVER_URL = "http://localhost:8080/api/";

    // HTTP client
    public static OkHttpClient client = new OkHttpClient();

    // JSON mediatype
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // GSON
    public static final Gson gson = new Gson();

    // entrypoint
    public static void main(String[] args) throws IOException  {
        // sample user
        User user = new User("wenjalan", "wenjalan", User.Gender.MALE);

        // sample group
        Group group = new Group(new StartLocation("HUB"), new EndLocation("West Campus"), System.currentTimeMillis());

        // post user to group test
//        postUser(user);

        // post group
        postGroup(group);

        // add user
//        joinGroup(user, group);

        // get group
//        Group g = getGroup(group.getId());
//        System.out.println("received group with id " + g.getId());
//
//        // get groups
//        List<Group> groups = getGroups();
//        System.out.println("current groups: " + groups);
//
//        // leave group
//        leaveGroup(user, group);
    }

    public static void postUser(User user) throws IOException {
        // create the body
        String json = gson.toJson(user);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "postuser")
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
    }

    public static void postGroup(Group group) throws IOException {
        // create the body
        String json = gson.toJson(group);
        RequestBody body = RequestBody.create(JSON, json);

        // build request
        Request request = new Request.Builder()
                .url(SERVER_URL + "postgroup")
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
    }

    public static void joinGroup(User user, Group group) throws IOException {
        // create the request
        String username = user.getUsername();
        String groupId = group.getId();
        HttpUrl url = HttpUrl.parse(SERVER_URL + "joingroup").newBuilder()
                .addQueryParameter("username", username)
                .addQueryParameter("groupId", groupId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
    }

    public static Group getGroup(String groupId) throws IOException {
        // create request
        HttpUrl url = HttpUrl.parse(SERVER_URL + "getgroup").newBuilder()
                .addQueryParameter("groupId", groupId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
        return gson.fromJson(response.body().string(), Group.class);
    }

    public static List<Group> getGroups() throws IOException {
        Request request = new Request.Builder()
                .url(SERVER_URL + "getgrouplist")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
        return gson.fromJson(response.body().string(), List.class); // should be a list
    }

    public static void leaveGroup(User user, Group group) throws IOException {
        // create the request
        String username = user.getUsername();
        String groupId = group.getId();
        HttpUrl url = HttpUrl.parse(SERVER_URL + "leavegroup").newBuilder()
                .addQueryParameter("username", username)
                .addQueryParameter("groupId", groupId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);
    }

}
