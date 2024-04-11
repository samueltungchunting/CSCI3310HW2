package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//
// Name: TUNG Chun Ting
// SID:  1155160200

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BuildingListAdapter mAdapter;

    // Initially a list storing image path
    // TODO: replace with another data structure to store the JSON fields
    private LinkedList<String> mImagePathList    = new LinkedList<>();
    private LinkedList<Float> mCrowdednessList   = new LinkedList<>();
    private LinkedList<String> mBuildingNameList = new LinkedList<>();
    private LinkedList<String> mRoomNameList     = new LinkedList<>();

    private final String mRawFilePath = "android.resource://edu.cuhk.csci3310.curoom/raw/";
    private final String mAppFilePath = "/data/data/edu.cuhk.csci3310.curoom/";
    private final String mDrawableFilePath = "android.resource://edu.cuhk.csci3310.curoom/drawable/";

    // TODO:
    // You may define more data members as needed
    // ... Rest of MainActivity code ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String jsonData = readJsonFile(R.raw.cu_buildings);
            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            JsonArray buildingsArray = jsonObject.getAsJsonArray("buildings");

            SharedPreferences spData = getSharedPreferences("updateRoom", Context.MODE_PRIVATE);

            int imageCount = Math.min(buildingsArray.size(), 6);

            for (int i = 0; i < imageCount; i++) {
                JsonObject buildingObject = buildingsArray.get(i).getAsJsonObject();
                String picRoom      = buildingObject.get("pic_room").getAsString();
                String fileNameWithoutExtension = picRoom.substring(0, picRoom.lastIndexOf('.'));
                String imagePath = mDrawableFilePath + fileNameWithoutExtension;
                String buildingName = buildingObject.get("building_partial_id").getAsString();
                String roomName     = buildingObject.get("room_name").getAsString();
                float  crowdedness   = buildingObject.get("crowdedness").getAsFloat();

                if (spData.contains("roomName"+i)) {
                    roomName    = spData.getString("roomName" + i, "");
                    crowdedness = spData.getFloat("crowdednedd" + i, 0.0f);
                }

                // Log.i("onCreate: 334455", imagePath);
                // Add the image path to mImagePathList
                mImagePathList.addLast(imagePath);
                mBuildingNameList.addLast(buildingName);
                mRoomNameList.addLast(roomName);
                mCrowdednessList.addLast(crowdedness);
            }

            // Now mImagePathList contains the file paths for the images
            // You can use this list to display images or perform other operations
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed,
        // initially just a list of image path
        // TODO: Update and pass more information as needed
        mAdapter = new BuildingListAdapter(this, mImagePathList, mBuildingNameList, mRoomNameList, mCrowdednessList);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        // TODO: Update the layout manager
        //  i.e. Set up Grid layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    // TODO:
    // Overriding extra callbacks, e.g. onStop(), onActivityResult(), etc.
    // as well as other utility method for JSON file read here
    private String readJsonFile(int resourceId) throws IOException {
        Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(resourceId);
        InputStreamReader reader = new InputStreamReader(inputStream);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024];
        int charsRead;

        while ((charsRead = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, charsRead);
        }

        return builder.toString();
    }

}
