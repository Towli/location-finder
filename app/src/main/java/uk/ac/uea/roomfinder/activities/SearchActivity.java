package uk.ac.uea.roomfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import uk.ac.uea.roomfinder.R;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;

    ArrayAdapter<String> adapter;

    String[] testItems = {"Location 1", "Location 2", "Location 3", "Location 4", "Location 5",
            "Location 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search);

        listView = (ListView)findViewById(R.id.list_view);
        searchView = (SearchView)findViewById(R.id.search_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testItems);

        listView.setAdapter(adapter);
    }



}
