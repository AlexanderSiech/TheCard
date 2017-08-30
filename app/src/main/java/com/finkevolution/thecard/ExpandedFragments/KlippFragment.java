package com.finkevolution.thecard.ExpandedFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.finkevolution.thecard.Activites.ExpandedFragment;
import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;

/**
 * Created by Girondins on 22/08/17.
 */

public class KlippFragment extends Fragment {
    private Card card;
    private ExpandedFragment expandedFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_klipp_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");

        Button qrBtn = (Button) v.findViewById(R.id.qrbuttonID);
        TextView tv = (TextView) v.findViewById(R.id.qrTextview);



        return v;
    }

}
