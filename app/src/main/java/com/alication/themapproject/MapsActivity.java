package com.alication.themapproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private static final LatLng EVRST = new LatLng(27.988119,86.9074655);
    private static final LatLng ETNA = new LatLng(37.7510032,14.9759253);
    private static final LatLng VZVJ = new LatLng(40.8223984,14.420151);

    private Marker mEvrst;
    private Marker mEtna;
    private Marker mVzvj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Marker> markerList = new ArrayList<>();

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mEvrst = mMap.addMarker(new MarkerOptions().position(EVRST).title("Everest"));
        mEvrst.setTag(0);
        markerList.add(mEvrst);

        mEtna = mMap.addMarker(new MarkerOptions().position(ETNA).title("Etna")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mEtna.setTag(0);
        markerList.add(mEtna);

        mVzvj = mMap.addMarker(new MarkerOptions().position(VZVJ).title("Vesuvio")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mVzvj.setTag(0);
        markerList.add(mVzvj);

        mMap.setOnMarkerClickListener(this);

        for (Marker m : markerList) {

            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2));
        }




//        LatLng everest = new LatLng(27.988119,86.9074655);
//        mMap.addMarker(new MarkerOptions().position(everest).title("Marker in Everest")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(everest, 10));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Integer clickCount = (Integer) marker.getTag();
        if (clickCount != null) {
            clickCount = clickCount +1;

            marker.setTag(clickCount);

            Toast.makeText(this, marker.getTitle() + " has been clicked " + clickCount + " times", Toast.LENGTH_SHORT ).show();
        }
        return false;
    }
}
