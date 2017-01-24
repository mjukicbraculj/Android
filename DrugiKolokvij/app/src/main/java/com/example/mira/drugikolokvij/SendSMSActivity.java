package com.example.mira.drugikolokvij;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class SendSMSActivity extends AppCompatActivity {

    private int MY_PERM=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, MY_PERM);

    }

    public void sendSMSClick(View view){
        sendSMS("5554", "Neki moj tekst koji saljem.");
    }

    //sends an SMS message to another device
    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public void onSMSIntentClick(View v) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //for new versions
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + 5554));
            i.putExtra("sms_body", "Moj tekst koji saljem.");
            startActivity(i);

        }
        else // For early versions, do what worked before.
        {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setType("vnd.android-dir/mms-sms");
            i.putExtra("address", "5556");
            i.putExtra("sms_body","Moj tekst koji saljem.");
            startActivity(i);
        }



    }
}
