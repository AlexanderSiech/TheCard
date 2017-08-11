package com.finkevolution.thecard.Activites;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finkevolution.thecard.Controller;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;
import com.finkevolution.thecard.ShopsAdapter;

import java.util.ArrayList;

/**
 * Created by Girondins on 11/08/17.
 */

public class CardlistFragment extends android.support.v4.app.Fragment{
    private RecyclerView mRecyclerView;
    private ShopsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Controller controller;
    private ArrayList<Card> cardList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.app_main_fragment, container, false);
        cardList = (ArrayList<Card>) getArguments().getSerializable("cardlist");



        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ShopsAdapter(cardList,controller);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

}
