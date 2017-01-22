package com.example.mira.drugikolokvij;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void SQLiteClick(View view){
        Intent i = new Intent(this, SQLiteDB.class);
        startActivity(i);

    }

    public void ListContactsClick(View view){
        Intent i = new Intent(this, ContactListActivity.class);
        startActivity(i);
    }

    public void MyContentProviderClick(View view){
        Intent i = new Intent(this, MyContentProvider.class);
        startActivity(i);
    }



}
