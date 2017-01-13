package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;

import uk.ac.uea.roomfinder.R;
import uk.ac.uea.roomfinder.framework.implementation.AndroidCSVParser;
import uk.ac.uea.roomfinder.framework.implementation.Site;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        Site s = new Site();
        s.setBuildings(new AndroidCSVParser().parse("map_data.csv", this));
        System.out.println(s.getBuildings().get(0).getName());
    }

    public void browseActivity(View view) {
        Intent i = new Intent(this, BrowseActivity.class);
        startActivity(i);
    }

    public void searchActivity(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }
}
