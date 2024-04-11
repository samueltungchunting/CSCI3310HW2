package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//
// Name: TUNG Chun Ting
// SID:  1155160200

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class BuildingListAdapter extends Adapter<BuildingListAdapter.BuildingViewHolder>  {
    private Context context;
    private LayoutInflater mInflater;

    private final LinkedList<String> mImagePathList;
    private final LinkedList<String> mBuildingNameList;
    private final LinkedList<String> mRoomNameList;
    private final LinkedList<Float> mCrowdednessList;

    private static final int REQUEST_CODE_DETAIL = 1001;

    public BuildingListAdapter(Context context, LinkedList<String> imagePathList, LinkedList<String> mBuildingNameList, LinkedList<String> mRoomNameList, LinkedList<Float> mCrowdednessList) {
        mInflater = LayoutInflater.from(context);
        this.mImagePathList    = imagePathList;
        this.mBuildingNameList = mBuildingNameList;
        this.mRoomNameList     = mRoomNameList;
        this.mCrowdednessList  = mCrowdednessList;
    }

    class BuildingViewHolder extends RecyclerView.ViewHolder {

        ImageView buildingImageItemView;
        RatingBar buildingCrowdednessBar;

        final BuildingListAdapter mAdapter;

        public BuildingViewHolder(View itemView, BuildingListAdapter adapter) {
            super(itemView);
            buildingImageItemView  = itemView.findViewById(R.id.image);
            buildingCrowdednessBar = itemView.findViewById(R.id.buildingBar);
            this.mAdapter = adapter;

            // Event handling registration, page navigation goes here
            // Event handling registration, page navigation goes here
            buildingImageItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the item that was clicked.
                    int position = getLayoutPosition();
//                    Toast t = Toast.makeText(v.getContext(), "Position " + position + " is clicked", Toast.LENGTH_SHORT);
//                    t.show();
                    try {
                        // Create an Intent to start DetailActivity
                        Intent intent = new Intent(v.getContext(), DetailActivity.class);

                        // Pass data to DetailActivity using extras
                        intent.putExtra("imagePath", mImagePathList.get(position));
                        intent.putExtra("crowdedness", mCrowdednessList.get(position));
                        intent.putExtra("buildingName", mBuildingNameList.get(position));
                        intent.putExtra("roomName", mRoomNameList.get(position));
                        intent.putExtra("position", position);

                        // Start the activity
                        ((Activity) v.getContext()).startActivityForResult(intent, REQUEST_CODE_DETAIL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // End of ViewHolder initialization
        }
    }

    @NonNull
    @Override
    public BuildingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.imagelist_item, parent, false);
        return new BuildingViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingViewHolder holder, int position) {
        String mImagePath = mImagePathList.get(position);
        Uri uri = Uri.parse(mImagePath);
        float crowedness = mCrowdednessList.get(position);
        // Update the following to display correct information based on the given position


        // Set up View items for this row (position), modify to show correct information read from the CSV
        holder.buildingImageItemView.setImageURI(uri);
        holder.buildingCrowdednessBar.setRating(crowedness);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mImagePathList.size();
    }

}
