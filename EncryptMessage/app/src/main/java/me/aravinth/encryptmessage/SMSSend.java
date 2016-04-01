package me.aravinth.encryptmessage;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SMSSend extends AppCompatActivity {

    EditText phoneno,email,message,keyspec;
    Button send,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssend);
        phoneno =(EditText) findViewById(R.id.phonenosend);
        email=(EditText)findViewById(R.id.emailsend);
        message =(EditText) findViewById(R.id.messagesend);
        send=(Button)findViewById(R.id.sendText);
        cancel=(Button)findViewById(R.id.cncl);
//        keyspec =(EditText) findViewById(R.id.keysend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("--->", "Enterinng clikk");
                new smssendTask().execute();

            }
        });

    }
    public class smssendTask extends AsyncTask<Void,Void,Void>
    {
//        static String IV = "AAAAAAAAAAAAAAAA";
        String phone=phoneno.getText().toString();
        String msg=message.getText().toString();
        String mail=email.getText().toString();
        String mailtext="Aravinth is pavam";

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {

            //key ll be generated and msg will be send
            // key has to be mailed

            Log.e("--->","Enterinng back");

            //encryption
            StringBuffer sb=new StringBuffer();
            try {
                KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
                KeyGen.init(128);
                SecretKey SecKey = new SecretKeySpec("1234567890123456".getBytes(), "AES");

                Cipher AesCipher = Cipher.getInstance("AES");

                System.out.println(SecKey);
                byte[] byteText = "Your Plain Text Here".getBytes();

                AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
                byte[] byteCipherText = AesCipher.doFinal(byteText);

                for(byte y:byteCipherText)
                    sb.append(y);

            } catch (Exception e) {
                e.printStackTrace();
            }




            Log.e("--->","Enterinng sms");
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,"AAANSJNA",null,null);

            Log.e("--->", "Enterinng mail");
            String url = "http://172.22.106.8:3000/index/sendmail?email="+mail+"&text=a"+sb.toString();

            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                } else {
                    Log.e("==>", "Failed to download file");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }
    }



}
