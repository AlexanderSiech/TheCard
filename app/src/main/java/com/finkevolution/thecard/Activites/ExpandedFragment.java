package com.finkevolution.thecard.Activites;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.finkevolution.thecard.Controller;
import com.finkevolution.thecard.ExpandedFragments.ContactFragment;
import com.finkevolution.thecard.ExpandedFragments.InfoFragment;
import com.finkevolution.thecard.ExpandedFragments.KlippFragment;
import com.finkevolution.thecard.ExpandedFragments.MapFragment;
import com.finkevolution.thecard.ExpandedFragments.OpenFragment;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;

/**
 * Created by Girondins on 2017-08-11.
 */

public class ExpandedFragment extends Fragment {
    private Controller controller;
    private Card card;
    private int current = 0;
    private Button infoBtn,openBtn,mapBtn,contactBtn,klippBtn;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   ActivityCompat.postponeEnterTransition(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_maincontainer_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");
        infoBtn = (Button) v.findViewById(R.id.infoBtn);
        klippBtn = (Button) v.findViewById(R.id.klippBtn);
        openBtn = (Button) v.findViewById(R.id.openBtn);
        mapBtn  = (Button) v.findViewById(R.id.mapBtn);
        contactBtn = (Button) v.findViewById(R.id.contactBtn);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfoFragment();
            }
        });

        klippBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKlippFragment();
            }
        });

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOpenFragment();
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.requestNewPosition();
                setMapFragment();
            }
        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContactFragment();
            }
        });

        setInfoFragment();


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ImageView imageView = (ImageView) view.findViewById(R.id.sharedTestID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(card.getShop().getName());
        }

        imageView.setImageResource(card.getShop().getImageSource());



    }


    public void setInfoFragment(){
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card",card);
        InfoFragment info = new InfoFragment();
        info.setArguments(cardBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(current < 0){
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        }

        if(current > 0){
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        }

        current = 0;
        changeClickable();
        ft.replace(R.id.fraglayoutcontain, info);
        ft.commit();
    }

    public void setKlippFragment(){
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card",card);
        KlippFragment klipp = new KlippFragment();
        klipp.setArguments(cardBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if(current < 1){
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        }

        if(current > 1){
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        }

        current = 1;
        changeClickable();
        ft.replace(R.id.fraglayoutcontain, klipp);
        ft.commit();
    }

    public void setOpenFragment(){
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card",card);
        OpenFragment open = new OpenFragment();
        open.setArguments(cardBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if(current < 2){
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        }

        if(current > 2){
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        }

        current = 2;
        changeClickable();
        ft.replace(R.id.fraglayoutcontain, open);
        ft.commit();
    }

    public void setMapFragment(){
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card",card);
        cardBundle.putSerializable("userPos",controller.getUserPosition());
        MapFragment map = new MapFragment();
        map.setArguments(cardBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if(current < 3){
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        }

        if(current > 3){
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        }
        current = 3;
        changeClickable();
        ft.replace(R.id.fraglayoutcontain, map);
        ft.commit();
    }

    public void setContactFragment(){
        Bundle cardBundle = new Bundle();
        cardBundle.putSerializable("card",card);
        ContactFragment contact = new ContactFragment();
        contact.setArguments(cardBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        if(current < 4) {
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        }
        if(current > 4) {
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        }
        current = 4;
        changeClickable();
        ft.replace(R.id.fraglayoutcontain, contact);
        ft.commit();
    }


    public void changeClickable(){

        switch (current){
            case 0:
                infoBtn.setClickable(false);
                openBtn.setClickable(true);
                mapBtn.setClickable(true);
                contactBtn.setClickable(true);
                klippBtn.setClickable(true);
                break;
            case 1:
                infoBtn.setClickable(true);
                openBtn.setClickable(true);
                mapBtn.setClickable(true);
                contactBtn.setClickable(true);
                klippBtn.setClickable(false);
                break;
            case 2:
                infoBtn.setClickable(true);
                openBtn.setClickable(false);
                klippBtn.setClickable(true);
                mapBtn.setClickable(true);
                contactBtn.setClickable(true);
                break;
            case 3:
                infoBtn.setClickable(true);
                openBtn.setClickable(true);
                mapBtn.setClickable(false);
                contactBtn.setClickable(true);
                klippBtn.setClickable(true);
                break;
            case 4:
                infoBtn.setClickable(true);
                klippBtn.setClickable(true);
                mapBtn.setClickable(true);
                openBtn.setClickable(true);
                contactBtn.setClickable(false);

        }
    }


    public void setController(Controller controller){
        this.controller = controller;
    }

    public boolean checkCameraPermission(){
        return controller.checkCameraPermission();
    }


}
