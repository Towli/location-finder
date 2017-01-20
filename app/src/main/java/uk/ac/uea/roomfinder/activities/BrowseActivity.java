package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.uea.framework.implementation.Building;
import uk.ac.uea.framework.implementation.Site;
import uk.ac.uea.roomfinder.R;

public class BrowseActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> testItems;
    Site site;
    List<String> buildingNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        /* Get Serialized Site object from Intent */
        site = (Site)getIntent().getSerializableExtra("site");

        /* Set list of building names for the adapter */
        buildingNames = new ArrayList<>();
        for (Building b : site.getBuildings())
                buildingNames.add(b.getName());

        listView = (ListView)findViewById(R.id.location_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buildingNames);
        listView.setAdapter(adapter);

        /* Set an OnClick listener for items in ListView */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BrowseActivity.this, DetailsActivity.class);
                Building selected = site.getBuildings().get((int)id);
                i.putExtra("building", selected);
                startActivity(i);
            }
        });
    }

    /**
     * A method for populating a test array for prototyping
     * @return
     */
    ArrayList<String> populateTestList(int size) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < size; i++) {
            list.add("Location " + i);
        }
        return list;
    }
}
