package uk.ac.uea.roomfinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.uea.roomfinder.R;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> testItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search);

        /* Populate array for test purposes */
        testItems = populateTestList(20);
        listView = (ListView)findViewById(R.id.list_view);
        searchView = (SearchView)findViewById(R.id.search_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testItems);
        listView.setAdapter(adapter);

        /* Set query listener on SearchView */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        /* Set an OnClick listener for items in ListView */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, DetailsActivity.class);
                i.putExtra("id", id);
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
