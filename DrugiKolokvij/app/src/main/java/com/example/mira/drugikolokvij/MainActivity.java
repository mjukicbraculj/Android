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

    public void SendSMSClick(View view){
        Intent i = new Intent(this, SendSMSActivity.class);
        startActivity(i);
    }

    public void SendMailClick(View view){
        Intent i = new Intent(this, SendMailActivity.class);
        startActivity(i);
    }

    public void HttpClick(View view){
        Intent i = new Intent(this, HttpConnectionActivity.class);
        startActivity(i);
    }

    public void ServiceClick(View view){
        Intent i = new Intent(this, ServiceActivity.class);
        startActivity(i);
    }

    public void NotificationClick(View view){
        Intent i = new Intent(this, NotificationActivity.class);
        startActivity(i);
    }

    public void ShowMapClick(View view){
        startActivity(new Intent(this, MapsActivity.class));
    }
}
