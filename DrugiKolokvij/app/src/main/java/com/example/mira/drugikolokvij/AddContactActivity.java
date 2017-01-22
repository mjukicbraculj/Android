package com.example.mira.drugikolokvij;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = new DBAdapter(this);
    }

    public void saveClick(View view){
        db.open();
        db.insertContact(((EditText)findViewById(R.id.nameEditText)).getText().toString(),
                ((EditText)findViewById(R.id.emailEditText)).getText().toString());
        db.close();
        finish();
    }
}
