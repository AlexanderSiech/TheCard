

package com.mah.ae7768.thecard.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mah.ae7768.thecard.ImageRequester;
import com.mah.ae7768.thecard.Photo;
import com.mah.ae7768.thecard.R;
import com.mah.ae7768.thecard.RecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageRequester.ImageRequesterResponse {

    private ArrayList<Photo> mPhotosList;
    private ImageRequester mImageRequester;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initilize();
    }

    private void initilize() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mGridLayoutManager = new GridLayoutManager(this, 2);   // tillfällig

        mPhotosList = new ArrayList<>();
        mAdapter = new RecyclerAdapter(mPhotosList);

        //setRecyclerViewScrollListener();
       // setRecyclerViewItemTouchListener();

        mRecyclerView.setAdapter(mAdapter);
        mImageRequester = new ImageRequester(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPhoto(); // hämtar en bild
        //fixar i ImageRequester
    }


    private void requestPhoto() {

        try {
            mImageRequester.getPhoto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedNewPhoto(final Photo newPhoto) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPhotosList.add(newPhoto);
                mAdapter.notifyItemInserted(mPhotosList.size());
            }
        });
    }

  /*
    METHOD checks to see what Layout is present and retuns the postion
    of the last visable item
    Layouts: Liniear or grid
   */

    private int getLastVisibleItemPosition() {
        int itemCount;

        if (mRecyclerView.getLayoutManager().equals(mLinearLayoutManager)) {
            itemCount = mLinearLayoutManager.findLastVisibleItemPosition();

        } else {
            itemCount = mGridLayoutManager.findLastVisibleItemPosition();
        }
        return itemCount;
    }

    /*
    Method handles a ScrollListener added to the Recycle View to to retrevie the count
    of the items in its LayoutManager and calculates the last visable photo index.
    then compares these numbers and if they match then a new photo is requested

    private void setRecyclerViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                int totalItemCount = mRecyclerView.getLayoutManager().getItemCount();
                while (!mImageRequester.isLoadingData() && totalItemCount == getLastVisibleItemPosition() + 1) {
                    requestPhoto();


                }
            }
        });
    }

     private void setRecyclerViewItemTouchListener() {
        // SKA ÄNDRA ANIMATION O SE VILKEN VI SKA HA OCH VAD SOM SKER NÄR MAN KLICKAR
        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                //2
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                // IFALL VI VILL ATT ANVÄNDAREN SKA KUNNA SWIPA BORT KORT KAN HA EN SPÄRR SOM DRAS
                //3
                int position = viewHolder.getAdapterPosition();
                mPhotosList.remove(position);
                mRecyclerView.getAdapter().notifyItemRemoved(position);
            }
        };

        //4
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

     */

    /*
    This code checks to see what LayoutManager your RecyclerView is using, and then:
    If it’s using the LinearLayoutManager, it swaps in the GridLayoutManager
    It requests a new photo if your grid layout only has one photo to show
    If it’s using the GridLayoutManager, it swaps in the LinearLayoutManager
     */
    private void changeLayoutmanager() {
        if (mRecyclerView.getLayoutManager().equals(mLinearLayoutManager)) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);

            if (mPhotosList.size() == 1) {
                requestPhoto();
            }
        } else {
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_recycler_manager) {
            changeLayoutmanager();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
