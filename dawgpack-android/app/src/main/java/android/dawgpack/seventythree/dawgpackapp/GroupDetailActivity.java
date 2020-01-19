package android.dawgpack.seventythree.dawgpackapp;

import android.dawgpack.seventythree.dawgpackapp.model.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        // set group details depending on what's in Dawg Pack
        // Group group = DawgPack.getInstance().getCurrentGroup();
        DawgPack.getInstance().getGroup("0", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // get the group
                Group group = new Gson().fromJson(response.body().string(), Group.class);

                // group header
                TextView header = findViewById(R.id.groupIdHeader);
                header.setText("Group id #" + group.getId());

                // group starting location
                TextView startLocationText = findViewById(R.id.startLocationText);
                startLocationText.setText("Starting at: " + group.getStartLocation().getName());

                // group destination location
                TextView endLocationText = findViewById(R.id.endLocationText);
                endLocationText.setText("Destination: " + group.getEndLocation().getName());

                // group users number
                TextView usersCountText = findViewById(R.id.usersText);
                usersCountText.setText("Users: 1");

            }
        });

        // attach listeners
        Button joinLeaveButton = findViewById(R.id.joinLeaveButton);
        joinLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo: leave the group
                finish();
            }
        });
    }
}
