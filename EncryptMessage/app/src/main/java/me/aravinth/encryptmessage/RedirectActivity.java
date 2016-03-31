package me.aravinth.encryptmessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RedirectActivity extends AppCompatActivity {

    Button snd,rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);
        snd=(Button)findViewById(R.id.snd);
        rcv=(Button)findViewById(R.id.rcv);

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RedirectActivity.this,SMSSend.class);
                startActivity(intent);

            }
        });
        rcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RedirectActivity.this,SMSDecrypt.class);
                startActivity(intent);


            }
        });
    }
}
