package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;

import uk.ac.uea.roomfinder.R;
import uk.ac.uea.roomfinder.framework.implementation.AndroidCSVParser;
import uk.ac.uea.roomfinder.framework.implementation.Building;
import uk.ac.uea.roomfinder.framework.implementation.Site;

public class MainActivity extends AppCompatActivity {

    Site site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        site = new Site();
        site.setBuildings(new AndroidCSVParser().parse("map_data.csv", this));
    }

    public void browseActivity(View view) {
        Intent i = new Intent(this, BrowseActivity.class);
        i.putExtra("site", site);
        startActivity(i);
    }

    public void searchActivity(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra("site", site);
        startActivity(i);
    }
}
