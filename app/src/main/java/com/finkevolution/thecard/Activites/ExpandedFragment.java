package com.finkevolution.thecard.Activites;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.finkevolution.thecard.Controller;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Girondins on 2017-08-11.
 */

public class ExpandedFragment extends android.support.v4.app.Fragment {
    private Controller controller;
    private Card card;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_main_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");
    //    AnimalItem animalItem = getArguments().getParcelable(EXTRA_ANIMAL_ITEM);
    //    String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);



        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        ImageView imageView = (ImageView) view.findViewById(R.id.sharedTestID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(card.getShop().getName());
        }

        Toast.makeText(getActivity(),"Shop is : " + card.getShop().getName(),Toast.LENGTH_SHORT).show();
        Log.d("Shop is Woob : " + card.getShop().getName()," " + card.getShop().getImageSource());

        Picasso.with(getActivity())
                .load(card.getShop().getImageSource())
                .noFade()
                .into(imageView, new Callback() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess() {
                          startPostponedEnterTransition();
                    }

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onError() {
                          startPostponedEnterTransition();
                    }
                });

    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
