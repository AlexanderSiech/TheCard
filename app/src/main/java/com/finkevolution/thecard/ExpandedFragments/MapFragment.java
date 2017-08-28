package com.finkevolution.thecard.ExpandedFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finkevolution.thecard.Objects.Card;
import com.finkevolution.thecard.Objects.LatLong;
import com.finkevolution.thecard.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Girondins on 22/08/17.
 */

public class MapFragment extends Fragment {
    private Card card;
    private MapView mMapView;
    private LatLong userPos;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_map_fragment, container, false);
        card = (Card) getArguments().getSerializable("card");
        userPos = (LatLong) getArguments().getSerializable("userPos");

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                PolylineOptions polyOptions = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
                polyOptions.add(new LatLng(card.getShop().getPos().getLatitude(),card.getShop().getPos().getLongitude()));
                polyOptions.add(new LatLng(userPos.getLatitude(),userPos.getLongitude()));

                // For dropping a marker at a point on the Map
                LatLng shopLatLng = new LatLng(card.getShop().getPos().getLatitude(), card.getShop().getPos().getLongitude());
                Marker shopMarker =  googleMap.addMarker(new MarkerOptions().position(shopLatLng).title(card.getShop().getName()).snippet(card.getShop().getAddress()));
                shopMarker.showInfoWindow();
                LatLng userLatLng = new LatLng(userPos.getLatitude(),userPos.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(userLatLng).title(getResources().getString(R.string.youarehere)));
                googleMap.addPolyline(polyOptions);
                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(userLatLng).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
