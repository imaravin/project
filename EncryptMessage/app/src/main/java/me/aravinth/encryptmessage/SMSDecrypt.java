package me.aravinth.encryptmessage;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
                try
                {
                    decryptmsg.setText(decrypt(encryptmsg.getText().toString().getBytes(),key.getText().toString()));
                }
                catch (Exception e)
                {
                    decryptmsg.setText("Failed");
                }
            }
        });
    }


    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{

        String IV = "AAAAAAAAAAAAAAAA";
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }

}
