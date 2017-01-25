package com.example.mira.drugikolokvij;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PeopleListActivity extends ListActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        String[] people = (String[])getIntent().getExtras().get("data");

        listView = getListView();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, people));


    }
}
