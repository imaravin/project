package com.example.encdecsms;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class DisplaySMSActivity extends Activity {

 EditText secretKey;
 TextView senderNum;
 TextView encryptedMsg;
 TextView decryptedMsg;
 Button submit;
 Button cancel;
 String originNum = "";
 String msgContent = "";

 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.onreceive);

 senderNum = (TextView) findViewById(R.id.senderNum);
 encryptedMsg = (TextView) findViewById(R.id.encryptedMsg);
 decryptedMsg = (TextView) findViewById(R.id.decryptedMsg);
 secretKey = (EditText) findViewById(R.id.secretKey);
 submit = (Button) findViewById(R.id.submit);
 cancel = (Button) findViewById(R.id.cancel);

 Bundle extras = getIntent().getExtras();
 if (extras != null) {
 originNum = extras.getString("originNum");
 msgContent = extras.getString("msgContent");
 senderNum.setText(originNum);
 encryptedMsg.setText(msgContent);
 } else {

 Toast.makeText(getBaseContext(), "Error Occurs!",
 Toast.LENGTH_SHORT).show();
 finish();
 }

 cancel.setOnClickListener(new View.OnClickListener() {

 public void onClick(View v) {
 finish();

 }
 });

 submit.setOnClickListener(new View.OnClickListener() {
 public void onClick(View v) {

 String secretKeyString = secretKey.getText().toString();             
 if (secretKeyString.length() > 0
 && secretKeyString.length() == 16) {
 try {

 byte[] msg = hex2byte(msgContent.getBytes());
 byte[] result = decryptSMS(secretKey.getText()
 .toString(), msg);
 decryptedMsg.setText(new String(result));

 } catch (Exception e) {

 decryptedMsg.setText("Message Cannot Be Decrypted!");
 }

 } else
 Toast.makeText(getBaseContext(),
 "You must provide a 16-character secret key!",
 Toast.LENGTH_SHORT).show();
 }
 });

 }
 public static byte[] hex2byte(byte[] b) {
 if ((b.length % 2) != 0)
 throw new IllegalArgumentException("hello");

 byte[] b2 = new byte[b.length / 2];

 for (int n = 0; n < b.length; n += 2) {
 String item = new String(b, n, 2);
 b2[n / 2] = (byte) Integer.parseInt(item, 16);
 }
 return b2;
 }

 public static byte[] decryptSMS(String secretKeyString, byte[] encryptedMsg)
 throws Exception {
 Key key = generateKey(secretKeyString);
 Cipher c = Cipher.getInstance("AES");
 c.init(Cipher.DECRYPT_MODE, key);
 byte[] decValue = c.doFinal(encryptedMsg);

 return decValue;
 }

 private static Key generateKey(String secretKeyString) throws Exception {
 Key key = new SecretKeySpec(secretKeyString.getBytes(), "AES");
 return key;
 }

}

