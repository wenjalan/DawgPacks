package android.dawgpack.seventythree.dawgpackapp;

import android.content.Context;
import android.content.Intent;
import android.dawgpack.seventythree.dawgpackapp.model.EndLocation;
import android.dawgpack.seventythree.dawgpackapp.model.Group;
import android.dawgpack.seventythree.dawgpackapp.model.StartLocation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        // attach listeners
        Button createGroupButton = findViewById(R.id.createGroupButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText startLocationField = (EditText) findViewById(R.id.startLocationField);
                String startLocation = startLocationField.getText().toString();
                EditText endLocationField = (EditText) findViewById(R.id.endLocationField);
                String endLocation = endLocationField.getText().toString();
                long id = 12345L;
                Group g = new Group(new StartLocation(startLocation), new EndLocation(endLocation), id);
                DawgPack dawgPack = DawgPack.getInstance();
                dawgPack.setCurrentGroup(g);
                dawgPack.postGroup(g, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d("D::", response.body().string());
                        Log.d("D::", "group posted");
                        finish();
                    }
                });
            }
        });
    }
}
