package com.finkevolution.thecard.Activites;

import android.Manifest;
import android.annotation.TargetApi;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import com.finkevolution.thecard.Objects.LatLong;
import com.finkevolution.thecard.R;
import com.finkevolution.thecard.ShopsAdapter;
import com.finkevolution.thecard.UserAdapter;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //GÖR OM private ArrayList<Photo> mPhotosList;
    private ArrayList<Card> shopList, userCardList, favoriteList;
    private RecyclerView mRecyclerView, userCardRecycler;
    private LinearLayoutManager mLinearLayoutManager, userLinearLayoutManager;
    private ShopsAdapter mAdapter;
    private UserAdapter userCardAdapter;
    private GridLayoutManager mGridLayoutManager;
    private Button showAll, showFavorite;
    private static Context context;
    private Controller controller;
    private LatLong userLatLong;
    private NfcAdapter nfcAdapter;
    private NfcManager nfcManager;

    //Permissions -----
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.NFC,
            Manifest.permission.CAMERA
    };
    private static final String[] NFC_PERMS = {
            Manifest.permission.NFC
    };
    private static final String[] CAMERA_PERMS = {
            Manifest.permission.CAMERA
    };
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST = 1337;
    private static final int NFC_REQUEST = INITIAL_REQUEST + 1;
    private static final int CAMERA_REQUEST = INITIAL_REQUEST + 2;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        postponeEnterTransition();
        //   getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        initialize();
        checkLaunchType(i);
        checkAllPermissions();


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

    private void checkLaunchType(Intent intent) {
        if (intent.getAction() == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            //controller.resolveIntent(intent);
        }
    }

    /**
     * Retrieves required data
     */
    private void retrieveUserData() {
        //  shopList = controller.getShops();
        userCardList = controller.getUserCards();
        favoriteList = controller.getUserFavorites();
    }

    /**
     * Setup and populate Recyclers
     */
    private void setupRecyclers() {
        userCardRecycler = (RecyclerView) findViewById(R.id.userCardRecycler);
        userLinearLayoutManager = new LinearLayoutManager(this);
        userCardRecycler.setLayoutManager(userLinearLayoutManager);
        userCardAdapter = new UserAdapter(userCardList);
        userCardRecycler.setAdapter(userCardAdapter);


    }

    /**
     * Setup and initializes buttons
     */
    private void setupButtons() {
        showAll = (Button) findViewById(R.id.showAllID);
        showFavorite = (Button) findViewById(R.id.showFavoriteID);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  userCardAdapter = new UserAdapter(userCardList);
              //  userCardRecycler.setAdapter(userCardAdapter);
                startQRScan();
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
    private void setupToolbar() {
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

    public void setFragment(Fragment fragment, boolean backstack) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //  ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        ft.replace(R.id.fragmentLayout, fragment, "MainFrag");
        if (backstack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void expandFragment(ImageView sharedImageView, Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addSharedElement(sharedImageView, ViewCompat.getTransitionName(sharedImageView));
        ft.replace(R.id.fragmentLayout, fragment);
        ft.addToBackStack(null);
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
        nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        nfcAdapter = nfcManager.getDefaultAdapter();
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        nfcAdapter = nfcManager.getDefaultAdapter();
        nfcAdapter.disableForegroundDispatch(this);

    }

    public void startQRScan(){
        Intent scanCode = new Intent(this,QRScanner.class);
        startActivityForResult(scanCode,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if (requestCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    Toast.makeText(this,barcode.displayValue+ " ",Toast.LENGTH_SHORT).show();
                }

            }else{
                    Toast.makeText(this,getString(R.string.codenotfound), Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //-------------------------- Permission section

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("Result is", requestCode + " Camera Request is " + hasPermission(Manifest.permission.CAMERA));
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (canAccessCamera()) {
                    //  doCameraThing();
                    Log.d("PERMSSION CAMERA", " GRANTED");
                } else {
                    //  bzzzt();
                }
                break;

            case NFC_REQUEST:
                if (canAccessNFC()) {
                    // doContactsThing();
                } else {
                    // bzzzt();
                }
                break;

            case LOCATION_REQUEST:
                Log.d("Case", LOCATION_REQUEST + " and ");
                if (canAccessLocation()) {
                    Log.d("Here We are", "ACCESS TO LOCATION");
                    retrieveLocation();
                } else {
                    // bzzzt();
                    Log.d("DENIED", "NO ACCESS TO LOCATION");
                }
                break;
        }
    }


    private boolean canAccessLocation() {
        return (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean canAccessCamera() {
        return (hasPermission(Manifest.permission.CAMERA));
    }

    private boolean canAccessNFC() {
        return (hasPermission(Manifest.permission.NFC));
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAllPermissions(){

        if(!canAccessLocation()){
            requestPermissions(LOCATION_PERMS,LOCATION_REQUEST);
            Log.d("PermissionCheck", "Location");
        }else{
            requestLocation();
        }


        if(!canAccessNFC()){
            requestPermissions(NFC_PERMS,NFC_REQUEST);
            Log.d("PermissionCheck", "NFC");
        }

        if(!canAccessCamera()){
            requestPermissions(CAMERA_PERMS,CAMERA_REQUEST);
            Log.d("PermissionCheck", "CAMERA");
        }

        if (!NfcAdapter.getDefaultAdapter(this).isEnabled())
        {
            Toast.makeText(getApplicationContext(), "Please activate NFC and press Back to return to the application!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(perm));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void retrieveLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        userLatLong = new LatLong(location.getLongitude(),location.getLatitude());

        controller.setUserLatLong(userLatLong);

    }


    public boolean checkCameraPermission(){
        if(!canAccessCamera()){
            return false;
        }else
            return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestCameraPermission(){
        requestPermissions(CAMERA_PERMS,CAMERA_REQUEST);
    }



    //For updating location
    @TargetApi(Build.VERSION_CODES.M)
    public void requestLocation(){
        requestPermissions(LOCATION_PERMS,LOCATION_REQUEST);
    }




}
