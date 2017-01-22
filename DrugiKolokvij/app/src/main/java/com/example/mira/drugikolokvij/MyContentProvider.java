package com.example.mira.drugikolokvij;

import android.app.ListActivity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MyContentProvider extends ListActivity {

    String[] books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_my_provider);

    }

    public void NewBookClick(View view){
        Intent i = new Intent(this, AddBookActivity.class);
        startActivity(i);

    }

    @Override
    protected void onResume(){

        super.onResume();
        showBooksClick();
    }

    private void showBooksClick(){
        Uri allTitles = Uri.parse(
                "content://com.example.mira.drugikolokvij.provider/books");

        Cursor c;
        if (android.os.Build.VERSION.SDK_INT <11) {
            //---before Honeycomb---
            c = managedQuery(allTitles, null, null, null,
                    "title desc");
        } else {
            //---Honeycomb and later---
            CursorLoader cursorLoader = new CursorLoader(
                    this,
                    allTitles, null, null, null,
                    "title desc");
            c = cursorLoader.loadInBackground();
        }
        ArrayList<String> booksArray = new ArrayList<String>();
        if (c.moveToFirst()) {
            do{
                booksArray.add(c.getString(c.getColumnIndex(BooksProvider.TITLE)));
            } while (c.moveToNext());
            books = new String[booksArray.size()];
            books = booksArray.toArray(books);
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, books));
        }
    }

    public void SearchBooks(View view){
        Intent i = new Intent(this, SelectedBooksListActivity.class);
        i.putExtra("searchString", ((EditText)findViewById(R.id.searchEditText)).getText().toString());
        startActivity(i);
    }

}
