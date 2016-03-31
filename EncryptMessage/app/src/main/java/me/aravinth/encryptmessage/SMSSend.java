package me.aravinth.encryptmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMSSend extends AppCompatActivity {

    EditText phoneno,email,message;
    Button send,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssend);
        phoneno =(EditText) findViewById(R.id.phoneno);
        email=(EditText)findViewById(R.id.email);
        message =(EditText) findViewById(R.id.message);
        send=(Button)findViewById(R.id.sendText);
        cancel=(Button)findViewById(R.id.cancel);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(phoneno.toString(),null,message.toString(),null,null);
            }
        });

    }
}
