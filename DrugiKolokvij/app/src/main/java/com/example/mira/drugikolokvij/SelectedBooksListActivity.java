package com.example.mira.drugikolokvij;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectedBooksListActivity extends ListActivity {

    String[] books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_books_list);

        searchBooks(getIntent().getStringExtra("searchString"));
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, books));
    }

    private void searchBooks(String searchString){
        Uri uri = Uri.parse("content://com.example.mira.drugikolokvij.provider/books");
        String[] projection = {BooksProvider.TITLE};
        String selection = BooksProvider.TITLE + " LIKE  ? || '%'";
        String[] args = new String[]{searchString};
        Cursor mCursor = getContentResolver().query(uri, projection, selection, args, null);
        ArrayList<String> booksArray = new ArrayList<>();
        if(mCursor.moveToFirst()){
            do{
                Log.d("SEARCH BOOK" , mCursor.getString(mCursor.getColumnIndex(BooksProvider.TITLE)));
                booksArray.add(mCursor.getString(mCursor.getColumnIndex(BooksProvider.TITLE)));
            }while (mCursor.moveToNext());
        }
        books = new String[booksArray.size()];
        books = booksArray.toArray(books);
    }
}
