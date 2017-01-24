package com.example.mira.drugikolokvij;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import static com.example.mira.drugikolokvij.R.id.map;
import static com.example.mira.drugikolokvij.R.id.status_bar_latest_event_content;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng AMFITEATAR =
            new LatLng(44.873222,13.850155);

    private static final LatLng BUJE =
            new LatLng(45.413944,13.665951);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map)).getMapAsync(this);


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
        if (mMap == null) {
            Toast.makeText(this, "Google Maps not available",
                    Toast.LENGTH_LONG).show();
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_sethybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.menu_showtraffic:
                mMap.setTrafficEnabled(true);
                break;
            case R.id.menu_zoomin:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;

            case R.id.menu_zoomout:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(AMFITEATAR) // Sets the center of the map to
                        // Golden Gate Bridge
                        .zoom(17)                   // Sets the zoom
                        .bearing(90) // Sets the orientation of the camera to east
                        .tilt(30)    // Sets the tilt of the camera to 30 degrees
                        .build();    // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));
                break;
            case R.id.menu_addmarker:
                mMap.addMarker(new MarkerOptions()
                        .position(AMFITEATAR)
                        .title("Arena")
                        .icon(BitmapDescriptorFactory
                                // .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                                .fromResource(R.drawable.pushpin)));
                break;
            case R.id.menu_getcurrentlocation:
                // ---get your current location and display a blue dot---
                try {
                    mMap.setMyLocationEnabled(true);
                }
                catch (SecurityException e) {

                }

                break;

            case R.id.menu_showcurrentlocation:
                Location myLocation = mMap.getMyLocation();
                LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());

                CameraPosition myPosition = new CameraPosition.Builder()
                        .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
                mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));
                //da ucita kartu na kraju
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.menu_lineconnecttwopoints:
                //---add a marker at Apple---
                mMap.addMarker(new MarkerOptions()
                        .position(BUJE)
                        .title("Buje")

                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE)));

                //---draw a line connecting Apple and Golden Gate Bridge---
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(AMFITEATAR, BUJE).width(5).color(Color.RED));
                Location start = new Location("amfiteatar");
                Location end = new Location("buje");
                start.setLatitude(AMFITEATAR.latitude);
                start.setLongitude(AMFITEATAR.longitude);
                end.setLatitude(BUJE.latitude);
                end.setLongitude(BUJE.longitude);
                Toast.makeText(this, Float.toString(end.distanceTo(start)), Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }
}
