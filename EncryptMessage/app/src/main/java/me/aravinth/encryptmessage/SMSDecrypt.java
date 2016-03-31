package me.aravinth.encryptmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMSDecrypt extends AppCompatActivity {

    EditText encryptmsg,key,decryptmsg;
    Button decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsdecrypt);
        encryptmsg = (EditText)findViewById( R.id.encryptmsg);
        key=(EditText)findViewById(R.id.key);
        decryptmsg=(EditText)findViewById(R.id.decryptmsg);
        decrypt=(Button)findViewById(R.id.decrypt);

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
