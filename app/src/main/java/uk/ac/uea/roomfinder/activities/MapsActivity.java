package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telecom.Call;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.uea.framework.implementation.Building;
import uk.ac.uea.framework.implementation.Point;
import uk.ac.uea.roomfinder.R;
import uk.ac.uea.roomfinder.fragments.DetailsFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DetailsFragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    private Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the MapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /* Get Serializable Extra */
        building = (Building)getIntent().getSerializableExtra("building");

        /* Initialise & inflate details fragment */
        Bundle bundle = new Bundle();
        bundle.putSerializable("building", building);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.details_container, detailsFragment).addToBackStack(null).commit();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the MapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Point point = building.getCenter();
        //Point point = (Point)getIntent().getSerializableExtra("point");

        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(point.getLatitude(), point.getLongitude());
        mMap.addMarker(new MarkerOptions().position(destination).title(building.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 18));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void test() {
        onBackPressed();
    }
}
