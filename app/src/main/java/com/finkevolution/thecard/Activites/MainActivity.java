


package com.finkevolution.thecard.Activites;

import android.annotation.TargetApi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finkevolution.thecard.Controller;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;
import com.finkevolution.thecard.ShopsAdapter;
import com.finkevolution.thecard.UserAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //GÖR OM private ArrayList<Photo> mPhotosList;
    private ArrayList<Card> shopList,userCardList,favoriteList;
    private RecyclerView mRecyclerView,userCardRecycler;
    private LinearLayoutManager mLinearLayoutManager,userLinearLayoutManager;
    private ShopsAdapter mAdapter;
    private UserAdapter userCardAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Button showAll,showFavorite;
    private static Context context;
    private Controller controller;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
     //   getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        initialize();
        checkLaunchType(i);
    }


    /**
     * Calls necessary functions for initialization
     */
    private void initialize() {

        MainActivity.context = getApplicationContext();
        controller = new Controller(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);   // tillfällig
        setupToolbar();
        retrieveUserData();
        setupButtons();
        setupRecyclers();

        //setRecyclerViewScrollListener();
        // setRecyclerViewItemTouchListener();

    }

    private void checkLaunchType(Intent intent){
        if(intent.getAction() == NfcAdapter.ACTION_NDEF_DISCOVERED)
        {
            //controller.resolveIntent(intent);
        }
    }

    /**
     * Retrieves required data
     */
    private void retrieveUserData(){
        shopList = controller.getShops();
        userCardList = controller.getUserCards();
        favoriteList = controller.getUserFavorites();
    }

    /**
     * Setup and populate Recyclers
     */
    private void setupRecyclers(){
        userCardRecycler = (RecyclerView) findViewById(R.id.userCardRecycler);
        userLinearLayoutManager = new LinearLayoutManager(this);
        userCardRecycler.setLayoutManager(userLinearLayoutManager);
        userCardAdapter = new UserAdapter(userCardList);
        userCardRecycler.setAdapter(userCardAdapter);


    }

    /**
     * Setup and initializes buttons
     */
    private void setupButtons(){
        showAll = (Button) findViewById(R.id.showAllID);
        showFavorite = (Button) findViewById(R.id.showFavoriteID);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCardAdapter = new UserAdapter(userCardList);
                userCardRecycler.setAdapter(userCardAdapter);
            }
        });

        showFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCardAdapter = new UserAdapter(favoriteList);
                userCardRecycler.setAdapter(userCardAdapter);
            }
        });
    }

    /**
     * Setup customized Toolbar
     */
    private void setupToolbar(){
        TextView toolTitle = (TextView) findViewById(R.id.toolbar_title);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        toolTitle.setText("Klippkortet");
        setSupportActionBar(myToolbar);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.BLACK);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // requestPhoto(); // hämtar en bild
        //fixar i ImageRequester
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

    /**
     * Method handles a ScrollListener added to the Recycle View to to retrevie the count
     * of the items in its LayoutManager and calculates the last visable photo index.
     * then compares these numbers and if they match then a new photo is requested
     */

    private void setRecyclerViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                int totalItemCount = mRecyclerView.getLayoutManager().getItemCount();
                while (totalItemCount == getLastVisibleItemPosition() + 1) {
                    //HÄMTA BILDER FRÅN NÄSTA

                }
            }
        });
    }

    /*
    This code checks to see what LayoutManager your RecyclerView is using, and then:
    If it’s using the LinearLayoutManager, it swaps in the GridLayoutManager
    It requests a new photo if your grid layout only has one photo to show
    If it’s using the GridLayoutManager, it swaps in the LinearLayoutManager
     */
    private void changeLayoutmanager() {
        if (mRecyclerView.getLayoutManager().equals(mLinearLayoutManager)) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);

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

    public static Context getContext() {
        return MainActivity.context;
    }

/**
    public void inflateStub(){
        final ViewStub stub = new ViewStub(this);
        final int[] touched = {0};
        stub.setLayoutResource(R.layout.testinflate);
        stub.setInflatedId(stub.generateViewId());


        final int inflatedId = stub.getInflatedId();
        final DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        dl.addView(stub);

        final View inflated = stub.inflate();
        final ImageButton outOfBound = (ImageButton) findViewById(R.id.imageButton);
        final LinearLayout v = (LinearLayout) findViewById(inflatedId);
        final Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);



        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                enableViews(findViewById(R.id.main_layout),true,true);
                mAdapter.isClickable = true;
                v.removeView(outOfBound);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dl.removeView(stub);
                stub.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        outOfBound.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(touched[0] == 0) {
                    inflated.startAnimation(slideUp);
                }

                touched[0]++;
                return false;
            }
        });


        Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        inflated.startAnimation(slide);
        enableViews(findViewById(R.id.main_layout),false,false);
        mAdapter.isClickable = false;
    }

    private void enableViews(View v, boolean enabled, final boolean scroll) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0;i<vg.getChildCount();i++) {
                enableViews(vg.getChildAt(i), enabled,scroll);
            }
        }
        v.setEnabled(enabled);

        mLinearLayoutManager = new LinearLayoutManager(this){
            @Override
        public boolean canScrollVertically() {
            return scroll;
        }
    };
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

 **/

    public void setFragment(android.support.v4.app.Fragment fragment, boolean backstack){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
      //  ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        ft.replace(R.id.fragmentLayout, fragment, "MainFrag");
        if(backstack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void expandFragment(ImageView sharedImageView, android.support.v4.app.Fragment fragment, boolean backstack){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.addSharedElement(sharedImageView,ViewCompat.getTransitionName(sharedImageView));
        ft.replace(R.id.fragmentLayout,fragment);



        if(backstack){
            ft.addToBackStack(null);
        }
        ft.commit();

    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        controller.resolveIntent(intent);
}

    @Override
    protected void onResume() {
        super.onResume();
        NfcManager nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter nfcAdapter = nfcManager.getDefaultAdapter();
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcManager nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter nfcAdapter = nfcManager.getDefaultAdapter();
        nfcAdapter.disableForegroundDispatch(this);

    }



}
