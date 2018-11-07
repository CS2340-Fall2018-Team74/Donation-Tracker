package edu.gatech.donationtracker.controller;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.dom.DOMLocator;

import edu.gatech.donationtracker.R;
import edu.gatech.donationtracker.model.Location;
import edu.gatech.donationtracker.model.Model;

public class Maps extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

//    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
//    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
//    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private List<Location> locations;

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        Map<String, Double[]> locations = getLocations();
       for (String name: locations.keySet()) {
           map.addMarker(new MarkerOptions()
           .position(new LatLng(locations.get(name)[0], locations.get(name)[1]))
           .title(name));
       }
//        map.addMarker(new MarkerOptions()
//                .position(new LatLng(37.7750, 122.4183))
//                .title("San Francisco")
//                .snippet("Population: 776733"));
        // Add some markers to the map, and add a data object to each marker.
//        mPerth = mMap.addMarker(new MarkerOptions()
//                .position(PERTH)
//                .title("Perth");
//        mPerth.setTag(0);
//
//        mSydney = mMap.addMarker(new MarkerOptions()
//                .position(SYDNEY)
//                .title("Sydney");
//        mSydney.setTag(0);
//
//        mBrisbane = mMap.addMarker(new MarkerOptions()
//                .position(BRISBANE)
//                .title("Brisbane");
//        mBrisbane.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    public Map<String, Double[]> getLocations() {
        return Model.getLongtitudeLatitude();
    }

}
