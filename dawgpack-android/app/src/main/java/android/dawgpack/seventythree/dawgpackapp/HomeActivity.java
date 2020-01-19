package android.dawgpack.seventythree.dawgpackapp;

import android.content.Context;
import android.content.Intent;
import android.dawgpack.seventythree.dawgpackapp.model.Group;
import android.dawgpack.seventythree.dawgpackapp.model.User;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.dawgpack.seventythree.dawgpackapp.DawgPack.SERVER_URL;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Context context = this.getApplicationContext();

        // attach button listeners
        Button viewGroupsButton = findViewById(R.id.viewGroupsButton);
        viewGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to view group activity
                Log.d("D::", "viewGroupsButton clicked");
                Intent i = new Intent(context, GroupListActivity.class);
                startActivity(i);
            }
        });

        Button createGroupButton = findViewById(R.id.createGroupButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to create group activity
                Log.d("D::", "createGroupButton clicked");
                Intent i = new Intent(context, CreateGroupActivity.class);
                startActivity(i);
            }
        });

        Button viewGroupButton = findViewById(R.id.viewGroupButton);
        viewGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to group detail activity
                Log.d("D::", "viewGroupButton clicked");
                Intent i = new Intent(context, GroupDetailActivity.class);
                startActivity(i);
            }
        });
    }

}
