package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import uk.ac.uea.framework.implementation.Building;
import uk.ac.uea.roomfinder.R;

public class DetailsActivity extends AppCompatActivity {

    TextView roomName;
    TextView location;
    Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details);
        roomName = (TextView)findViewById(R.id.name_tv);
        location = (TextView)findViewById(R.id.description_tv);

        /* Get intent and use passed data */
        building = (Building)getIntent().getSerializableExtra("building");
        roomName.setText(building.getName());
        location.setText(building.getCenter().toString());
    }

    public void mapActivity(View view) {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("point", building.getCenter());
        startActivity(i);
    }

}