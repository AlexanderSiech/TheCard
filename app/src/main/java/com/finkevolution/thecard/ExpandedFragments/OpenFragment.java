package com.finkevolution.thecard.ExpandedFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.R;

/**
 * Created by Girondins on 22/08/17.
 */

public class OpenFragment extends Fragment {
    private Card card;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_open_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");
        TextView monday = (TextView) v.findViewById(R.id.mondayTV);
        TextView tuesday = (TextView) v.findViewById(R.id.tuesdayTV);
        TextView wednesday = (TextView) v.findViewById(R.id.wednesdayTV);
        TextView thursday = (TextView) v.findViewById(R.id.thursdayTV);
        TextView friday = (TextView) v.findViewById(R.id.fridayTV);
        TextView saturday = (TextView) v.findViewById(R.id.saturdayTV);
        TextView sunday = (TextView) v.findViewById(R.id.sundayTV);

        monday.setText(getActivity().getResources().getString(R.string.monday) +" : " + card.getShop().getOpenHours().getDayAtIndex(0).getOpenHours());
        tuesday.setText(getActivity().getResources().getString(R.string.tuesday) +" : " + card.getShop().getOpenHours().getDayAtIndex(1).getOpenHours());
        wednesday.setText(getActivity().getResources().getString(R.string.wednesday) +" : " + card.getShop().getOpenHours().getDayAtIndex(2).getOpenHours());
        thursday.setText(getActivity().getResources().getString(R.string.thursday) +" : " + card.getShop().getOpenHours().getDayAtIndex(3).getOpenHours());
        friday.setText(getActivity().getResources().getString(R.string.friday) +" : " + card.getShop().getOpenHours().getDayAtIndex(4).getOpenHours());
        saturday.setText(getActivity().getResources().getString(R.string.saturday) +" : " + card.getShop().getOpenHours().getDayAtIndex(5).getOpenHours());
        sunday.setText(getActivity().getResources().getString(R.string.sunday) +" : " + card.getShop().getOpenHours().getDayAtIndex(6).getOpenHours());



        return v;
    }
}
