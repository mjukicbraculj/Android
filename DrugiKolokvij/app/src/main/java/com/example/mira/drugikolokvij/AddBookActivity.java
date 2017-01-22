package com.example.mira.drugikolokvij;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }
    public void AddBookClick(View view){
        ContentValues values = new ContentValues();
        values.put("title", ((EditText)
                findViewById(R.id.titleEditText)).getText().toString());
        values.put("isbn", ((EditText)
                findViewById(R.id.isbnEditText)).getText().toString());
        Uri uri = getContentResolver().insert(
                Uri.parse(
                        "content://com.example.mira.drugikolokvij.provider/books"),
                values);
        finish();
    }
}
