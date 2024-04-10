package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    // TODO:
    // Define other attributes as needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO:
        // 1. Obtain data (e.g. JSON) via Intent
        // 2. Setup Views based on the data received
        // 3. Perform necessary data-persistence steps

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // TODO:
        // Perform necessary data-persistence steps

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO:
        // Perform necessary data-persistence steps

    }

    // TODO:
    // Add more utility methods as needed

}