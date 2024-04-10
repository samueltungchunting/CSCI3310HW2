package edu.cuhk.csci3310.curoom;

// TODO:
// Include your personal particular here
//

import android.content.Context;
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

    class BuildingViewHolder extends RecyclerView.ViewHolder {

        ImageView buildingImageItemView;
        RatingBar buildingCrowdednessBar;

        final BuildingListAdapter mAdapter;

        public BuildingViewHolder(View itemView, BuildingListAdapter adapter) {
            super(itemView);
            buildingImageItemView = itemView.findViewById(R.id.image);
            buildingCrowdednessBar = itemView.findViewById(R.id.buildingBar);
            this.mAdapter = adapter;

            // Event handling registration, page navigation goes here
            // Event handling registration, page navigation goes here
            buildingImageItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the item that was clicked.
                    int position = getLayoutPosition();
                    Toast t = Toast.makeText(v.getContext(), "Position " + position + " is clicked", Toast.LENGTH_SHORT);
                    t.show();
                }
            });

            // End of ViewHolder initialization
        }
    }

    public BuildingListAdapter(Context context,
                            LinkedList<String> imagePathList) {
        mInflater = LayoutInflater.from(context);
        this.mImagePathList = imagePathList;
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
        // Update the following to display correct information based on the given position


        // Set up View items for this row (position), modify to show correct information read from the CSV
        holder.buildingImageItemView.setImageURI(uri);
        holder.buildingCrowdednessBar.setRating(3.0f);

    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mImagePathList.size();
    }

}
