package uk.ac.uea.roomfinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uk.ac.uea.roomfinder.R;

public class BrowseActivity extends AppCompatActivity {

    ListView listView;
    String[] testItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        /* Populate array for test purposes */
        testItems = populateTestArray();

        listView = (ListView)findViewById(R.id.location_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testItems);
        listView.setAdapter(adapter);
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
