package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uk.ac.uea.framework.implementation.AndroidCSVParser;
import uk.ac.uea.framework.implementation.AndroidInternalFileIO;
import uk.ac.uea.framework.implementation.Building;
import uk.ac.uea.framework.implementation.Point;
import uk.ac.uea.framework.implementation.Site;
import uk.ac.uea.roomfinder.R;
import uk.ac.uea.roomfinder.activities.BrowseActivity;
import uk.ac.uea.roomfinder.activities.SearchActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Site site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        new AndroidInternalFileIO().writeObject(buildings, "test", this);

        List<Building> test = (List<Building>) new AndroidInternalFileIO().readObject("test", this);
        System.out.println(test.get(0).getName());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            System.out.println("Nav item pressed: Home");
        } else if (id == R.id.nav_browse) {
            Intent i = new Intent(this, BrowseActivity.class);
            i.putExtra("site", site);
            startActivity(i);
        } else if (id == R.id.nav_search) {
            Intent i = new Intent(this, SearchActivity.class);
            i.putExtra("site", site);
            startActivity(i);
        } else if (id == R.id.nav_help) {
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
