package com.example.mira.drugikolokvij;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SQLiteDB extends ListActivity {

    DBAdapter db = new DBAdapter(this);
    String[] people;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_db);

        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }

    private void getAllContacts(){
        db.open();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor c = db.getAllContacts();
        if(c.moveToFirst()){
            do{
                arrayList.add(c.getString(1));
            }while (c.moveToNext());
        }
        people = new String[arrayList.size()];
        people = arrayList.toArray(people);
        db.close();

    }

    public void AddClick(View view){
        Intent i = new Intent(this, AddContactActivity.class);
        startActivity(i);
    }

    public void deleteClick(View view){
        //SparseBooleanArrays map integers to booleans
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        db.open();
        for(int i = 0; i < people.length; ++i){
            if(checked.get(i)){
                try {
                    Cursor cursor = db.getContactsByName(people[i]);    //assume that name is unique
                    db.deleteContact(Integer.parseInt(cursor.getString(0)));        //know that id exists
                    //Toast.makeText(this, "delete: " + data[0], Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        db.close();
        onResume();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getAllContacts();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, people));
    }

    public void onListItemClick(ListView parent, View v, int position, long id){
        //TODO write selected person's mail
    }

}
