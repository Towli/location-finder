package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.location.Location;
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
import uk.ac.uea.framework.implementation.DeviceLocation;
import uk.ac.uea.framework.implementation.DeviceMap;
import uk.ac.uea.framework.implementation.Point;
import uk.ac.uea.roomfinder.R;
import uk.ac.uea.roomfinder.fragments.DetailsFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DetailsFragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    private Building building;
    private DeviceLocation deviceLocation;
    private Point currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the MapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /* Get Serializable Extras */
        building = (Building)getIntent().getSerializableExtra("building");
        currentLocation = (Point)getIntent().getSerializableExtra("currentLocation");

        /* Initialise & inflate details fragment */
        Bundle bundle = new Bundle();
        bundle.putSerializable("building", building);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.details_container, detailsFragment).addToBackStack(null).commit();

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

        /* Destination location */
        Point point = building.getCenter();

        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(point.getLatitude(), point.getLongitude());
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(destination).title(building.getName()));
        mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 18));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRoutePressed() {
    }
}
