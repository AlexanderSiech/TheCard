package com.finkevolution.thecard;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finkevolution.thecard.Objects.Shop;
import com.finkevolution.thecard.Objects.User;

import java.util.ArrayList;

/**
 * Created by Girondins on 04/07/17.
 */



public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder> {
    private ArrayList<Shop> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView background,banner,stamps;

        public ViewHolder(View v) {
            super(v);
            background = (ImageView) v.findViewById(R.id.backgoundImgID);
            banner = (ImageView) v.findViewById(R.id.bannerImgID);
            stamps = (ImageView) v.findViewById(R.id.stampcountImgID);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ShopsAdapter(ArrayList<Shop> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View shopView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_shop, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(shopView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.background.setImageResource(mDataset.get(position).getImageSource());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();


    }}