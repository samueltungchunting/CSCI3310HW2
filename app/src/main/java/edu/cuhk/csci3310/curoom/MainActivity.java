package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BuildingListAdapter mAdapter;

    // Initially a list storing image path
    // TODO: replace with another data structure to store the JSON fields
    private LinkedList<String> mImagePathList = new LinkedList<>();

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

        // Initially put random data into the image list, modify to pass correct info read from JSON
        int num = (int) (Math.random()*6)+1;
        for(int i=1; i<=num; i++) {
            mImagePathList.addLast(mDrawableFilePath + "temp");
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed,
        // initially just a list of image path
        // TODO: Update and pass more information as needed
        mAdapter = new BuildingListAdapter(this, mImagePathList);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        // TODO: Update the layout manager
        //  i.e. Set up Grid layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // TODO:
    // Overriding extra callbacks, e.g. onStop(), onActivityResult(), etc.
    // as well as other utility method for JSON file read here




}
