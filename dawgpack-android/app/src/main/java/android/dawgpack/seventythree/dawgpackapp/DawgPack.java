package android.dawgpack.seventythree.dawgpackapp;

import android.dawgpack.seventythree.dawgpackapp.model.Group;
import android.dawgpack.seventythree.dawgpackapp.model.User;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// interfaces with the DawgPack Server
public class DawgPack {

    // the group this session is currently connected to
    private Group currentGroup = null;

    // server URL
    public static final String SERVER_URL = "http://10.19.12.38:8080/api/";

    // HTTP client
    public static OkHttpClient client;

    // JSON mediatype
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // GSON
    public static final Gson gson = new Gson();

    // singleton
    private static DawgPack instance = new DawgPack();

    // get instance
    public static DawgPack getInstance() {
        return instance;
    }

    // private constructor
    private DawgPack() {
        client = new OkHttpClient();
    }

    public void postUser(User user, Callback callback) {
        // create the body
        String json = gson.toJson(user);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "postuser")
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void postGroup(Group group, Callback callback) {
        // create the body
        String json = gson.toJson(group);
        RequestBody body = RequestBody.create(JSON, json);

        // build request
        Request request = new Request.Builder()
                .url(SERVER_URL + "postgroup")
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void joinGroup(User user, Group group, Callback callback) {
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
        call.enqueue(callback);
    }

    public void getGroup(String groupId, Callback callback) {
        // create request
        HttpUrl url = HttpUrl.parse(SERVER_URL + "getgroup").newBuilder()
                .addQueryParameter("groupId", groupId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void getGroups(Callback callback) {
        Request request = new Request.Builder()
                .url(SERVER_URL + "getgrouplist")
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void leaveGroup(User user, Group group, Callback callback) {
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
        call.enqueue(callback);
    }

    public Group getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }
}
