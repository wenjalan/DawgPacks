package android.dawgpack.seventythree.dawgpackapp;

import android.content.Context;
import android.content.Intent;
import android.dawgpack.seventythree.dawgpackapp.R;
import android.dawgpack.seventythree.dawgpackapp.model.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        // load the groups from Dawg Packs
        DawgPack.getInstance().getGroup("0", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // error, do nothing
                System.err.println("error retrieving groups");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.d("D::", "loaded groups from server:\n" + json);
                Group demoGroup = new Gson().fromJson(json, Group.class);
                List<Group> groups = new ArrayList();
                groups.add(demoGroup);
                populateList(groups);
            }
        });

    }

    private void populateList(final List<Group> groups) {
        final Context context = getApplicationContext();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout groupListLayout = findViewById(R.id.groupListLayout);
                for (Group g : groups) {
                    final String id = g.getId();
                    TextView textView = new TextView(context);
                    textView.setText("Group id #" + g.getId() + " (" + g.getStartLocation().getName() + " to " + g.getEndLocation().getName() + ")");
                    textView.setTextSize(24.0f);
                    textView.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // todo: clicking on a group brings you to the group's detail page
                                    Log.d("D::", "group list item " + id + " tapped");
                                    Intent i = new Intent(context, GroupDetailActivity.class);
                                    startActivity(i);
                                }
                            }
                    );
                    groupListLayout.addView(textView);
                }
            }
        });
    }

    private void printJson(String json) {
        LinearLayout groupListLayout = findViewById(R.id.groupListLayout);
        TextView textView = new TextView(this);
        textView.setText(json);
        groupListLayout.addView(textView);
    }

}
