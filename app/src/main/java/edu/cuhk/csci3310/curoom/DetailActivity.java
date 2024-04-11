package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//
// Name: TUNG Chun Ting
// SID:  1155160200

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    // TODO:
    // Define other attributes as needed
    private List<String> fullnessStatus = Arrays.asList("Empty", "Not Crowded", "Somewhat Crowded", "Overflowing");
    private String roomName = "";
    private float  crowdedness = 0;
    private float  NewCrowdedness = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO:
        // 1. Obtain data (e.g. JSON) via Intent
        // 2. Setup Views based on the data received
        // 3. Perform necessary data-persistence steps

        Intent intent = getIntent();

        ImageView imageLarge      = findViewById(R.id.image_large);
        EditText  editName        = findViewById(R.id.edit_name);
        TextView  textBuilding    = findViewById(R.id.text_building);
        TextView  textCrowdedness = findViewById(R.id.textCrowdedness);

        String imagePath    = intent.getStringExtra("imagePath");
        String buildingName = intent.getStringExtra("buildingName");
        String roomName     = intent.getStringExtra("roomName");
        float  crowdedness  = intent.getFloatExtra("crowdedness", 0);

        this.crowdedness    = intent.getFloatExtra("crowdedness", 0);
        this.NewCrowdedness = intent.getFloatExtra("crowdedness", 0);
        this.roomName       = intent.getStringExtra("roomName");


        imageLarge.setImageURI(Uri.parse(imagePath));
        editName.setText(roomName);
        textBuilding.setText(buildingName);
        textCrowdedness.setText(fullnessStatus.get((int) crowdedness));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // TODO:
        // Perform necessary data-persistence steps
        savedInstanceState.putString("roomName", roomName);
        savedInstanceState.putFloat("crowdedness", crowdedness);
        savedInstanceState.putFloat("NewCrowdedness", NewCrowdedness);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO:
        // Perform necessary data-persistence steps
        roomName = savedInstanceState.getString("roomName");
        crowdedness = savedInstanceState.getFloat("crowdedness");
        NewCrowdedness = savedInstanceState.getFloat("NewCrowdedness");
    }

    // TODO:
    // Add more utility methods as needed
    public void plusCrowdedness(View view) {
        // check if the crowdedness is less than 3, otherwise cant increase
        if(NewCrowdedness < 3) {
            NewCrowdedness = NewCrowdedness + 1;
        }
        TextView textCrowdedness = findViewById(R.id.textCrowdedness);
//        Log.i( "plusCrowdedness: ", String.valueOf((int) NewCrowdedness));
        textCrowdedness.setText(fullnessStatus.get((int) NewCrowdedness));
//        textCrowdedness.setText("testing_123");
    }

    public void minusCrowdedness(View view) {
        if(NewCrowdedness>0){
            NewCrowdedness--;
        }
        TextView textCrowdedness = findViewById(R.id.textCrowdedness);
        textCrowdedness.setText(fullnessStatus.get((int) NewCrowdedness));
    }

    public void cancelEntry(View view) {
        EditText editName = findViewById(R.id.edit_name);
        TextView textCrowdedness = findViewById(R.id.textCrowdedness);

        editName.setText(roomName);
        NewCrowdedness = crowdedness;
        textCrowdedness.setText(fullnessStatus.get((int) crowdedness));
    }

    public void saveEntry(View view) {
        EditText editName = findViewById(R.id.edit_name);
        String newRoomName = editName.getText().toString();
        int position = getIntent().getIntExtra("position", -1);
        SharedPreferences settings = this.getSharedPreferences("updateRoom", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("roomName"+position, newRoomName);
        editor.putFloat("crowdedness"+position, NewCrowdedness);
//        Log.i("saveEntry: 111", newRoomName);
//        Log.i("saveEntry: 222", String.valueOf((int) NewCrowdedness));
        editor.commit();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updatedRoomName", newRoomName);
        resultIntent.putExtra("updatedCrowdedness", NewCrowdedness);
        resultIntent.putExtra("position", position);

        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}