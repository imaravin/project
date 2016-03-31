package me.aravinth.encryptmessage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Signup extends AppCompatActivity {

    EditText username,passwd1,passwd2;
    Button submit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context=this;
        username =(EditText) findViewById(R.id.email);
        passwd1 =(EditText) findViewById(R.id.passwd1);
        passwd2 = (EditText)findViewById(R.id.passwd2);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwd2.getText().toString().equals(passwd1.getText().toString()))
                {

                    new SignUpTask(context).execute();
                }
                else
                {
                    AlertDialog.Builder goLogin = new AlertDialog.Builder(context);
                    goLogin.setMessage("password not matching");
                    goLogin.setCancelable(false);
                    goLogin.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertLogin = goLogin.create();
                    alertLogin.show();
                }
            }
        });


    }
    public class SignUpTask extends AsyncTask<Void,Void,Void>
    {
        String uname=username.getText().toString();
        String passwd=passwd1.getText().toString();
        String result="";
        Context context;

        public SignUpTask(Context context) {
            this.context=context;
        }

        @Override
        protected Void doInBackground(Void... params) {



            String url = "http://172.22.106.8:3000/index/signup?email="+uname+"&password="+passwd+"&name=cx";


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

            result=builder.toString();

            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(result.equalsIgnoreCase("success"))
            {
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
            else
            {
                AlertDialog.Builder goLogin = new AlertDialog.Builder(context);
                goLogin.setMessage("Register Failed");
                goLogin.setCancelable(false);
                goLogin.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertLogin = goLogin.create();
                alertLogin.show();
            }



        }


    }

}
