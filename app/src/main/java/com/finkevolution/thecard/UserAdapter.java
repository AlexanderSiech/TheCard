package com.finkevolution.thecard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.finkevolution.thecard.Activites.MainActivity;
import com.finkevolution.thecard.Objects.Card;

import java.util.ArrayList;

/**
 * Created by Girondins on 04/07/17.
 */



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<Card> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView background,banner,stamps;

        public ViewHolder(View v) {
            super(v);
            background = (ImageView) v.findViewById(R.id.backgoundImgIDUser);
            banner = (ImageView) v.findViewById(R.id.bannerImgIDUser);
            stamps = (ImageView) v.findViewById(R.id.stampcountImgIDUser);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter(ArrayList<Card> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View shopView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_user, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(shopView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.background.setImageResource(mDataset.get(position).getShop().getImageSource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.getContext(),mDataset.get(position).getShop().getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();


    }}