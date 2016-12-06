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

    String[] testItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search);

        /* Populate array for test purposes */
        testItems = populateTestArray();

        listView = (ListView)findViewById(R.id.list_view);
        searchView = (SearchView)findViewById(R.id.search_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testItems);

        listView.setAdapter(adapter);

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
    }

    /**
     * A method for populating a test array for prototyping
     * @return
     */
    String[] populateTestArray() {
        String[] array = new String[20];
        for(int i = 0; i < array.length; i++) {
            array[i] = "Location " + i;
        }
        return array;
    }

}
