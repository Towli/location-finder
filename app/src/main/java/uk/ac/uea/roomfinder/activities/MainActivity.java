package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uk.ac.uea.framework.implementation.AndroidCSVParser;
import uk.ac.uea.framework.implementation.Building;
import uk.ac.uea.framework.implementation.Point;
import uk.ac.uea.framework.implementation.Site;
import uk.ac.uea.roomfinder.R;

public class MainActivity extends AppCompatActivity {

    Site site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        site = new Site();
        List<List<String>> csv = new AndroidCSVParser().parse("map_data.csv", this);
        List<Building> buildings = new ArrayList<>();

        for(List<String> line : csv) {
            if(line.get(0).equals("building")) {
                buildings.add(new Building(line.get(1), new Point(line.get(2), line.get(3)),
                        line.get(5), line.get(4)));
            }
        }

        site.setBuildings(buildings);
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
