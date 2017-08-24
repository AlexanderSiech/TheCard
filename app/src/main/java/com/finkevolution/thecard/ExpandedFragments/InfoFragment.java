package com.finkevolution.thecard.ExpandedFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;
import com.finkevolution.thecard.ShopsAdapter;

import java.util.ArrayList;

/**
 * Created by Girondins on 22/08/17.
 */

public class InfoFragment extends Fragment {
    private Card card;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_info_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");
        TextView descript = (TextView) v.findViewById(R.id.descriptionTV);
        TextView header = (TextView) v.findViewById(R.id.headerTV);

        header.setText(card.getShop().getName());
        descript.setText(card.getShop().getShopDescription());
        descript.setMovementMethod(new ScrollingMovementMethod());

        return v;
    }
}
