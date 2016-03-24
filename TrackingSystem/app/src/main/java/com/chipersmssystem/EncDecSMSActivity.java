package com.chipersmssystem;
import java.security.Key;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.os.AsyncTask;
import com.example.trackingsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author sentamilpandi.m
 *
 */



public class EncDecSMSActivity extends Activity {
 /** Called when the activity is first created. */
 EditText recNum;
 EditText secretKey;
 EditText email_id;
 EditText msgContent;
 Button send;
 Button cancel;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.main);

 recNum = (EditText) findViewById(R.id.recNum);
 secretKey = (EditText) findViewById(R.id.secretKey);
 email_id = (EditText) findViewById(R.id.email);
 msgContent = (EditText) findViewById(R.id.msgContent);
 send = (Button) findViewById(R.id.Send);
 cancel = (Button) findViewById(R.id.cancel);
 cancel.setOnClickListener(new View.OnClickListener() {
 public void onClick(View v) {
 finish();
 }
 });

 send.setOnClickListener(new View.OnClickListener() {
 public void onClick(View v) {
 String recNumString = recNum.getText().toString();
 String secretKeyString = secretKey.getText().toString();
 String emailString = email_id.getText().toString();
 String msgContentString = msgContent.getText().toString();

  if (recNumString.length() > 0 && secretKeyString.length() > 0
 && msgContentString.length() > 0
 && secretKeyString.length() == 16) {
 byte[] encryptedMsg = encryptSMS(secretKeyString,
 msgContentString);
 String msgString = byte2hex(encryptedMsg);
 sendSMS(recNumString, msgString);
 ActiveTask task=new ActiveTask();
	task.execute();
 finish();

 } else
 Toast.makeText(
 getBaseContext(),
 "Please enter phone number, secret key and the message. Secret key must be 16 characters!",
 Toast.LENGTH_SHORT).show();
 }
 });

 }

 public static void sendSMS(String recNumString, String encryptedMsg) {
 try {


 SmsManager smsManager = SmsManager.getDefault();
 ArrayList<String> parts = smsManager.divideMessage(encryptedMsg);
 smsManager.sendMultipartTextMessage(recNumString, null, parts,
 null, null);

 } catch (Exception e) {
 e.printStackTrace();
 }

 }
 public static String byte2hex(byte[] b) {
 String hs = "";
 String stmp = "";
 for (int n = 0; n < b.length; n++) {
 stmp = Integer.toHexString(b[n] & 0xFF);
 if (stmp.length() == 1)
 hs += ("0" + stmp);
 else
 hs += stmp;
 }
 return hs.toUpperCase();
 }

 public static byte[] encryptSMS(String secretKeyString,
 String msgContentString) {

 try {
 byte[] returnArray;
 Key key = generateKey(secretKeyString);
 Cipher c = Cipher.getInstance("AES");
 c.init(Cipher.ENCRYPT_MODE, key);

 returnArray = c.doFinal(msgContentString.getBytes());

 return returnArray;

 } catch (Exception e) {
 e.printStackTrace();
 byte[] returnArray = null;
 return returnArray;
 }

 }

 private static Key generateKey(String secretKeyString) throws Exception {
 Key key = new SecretKeySpec(secretKeyString.getBytes(), "AES");
 return key;
 }
 private class ActiveTask extends AsyncTask<String,Void,Void> {
	 String secretKeyString = secretKey.getText().toString();
	 String emailString = email_id.getText().toString();
	 
		String res=null;
		
		@Override
		protected void onPreExecute() {
			Toast.makeText(getApplicationContext(), secretKeyString+".."+emailString, Toast.LENGTH_LONG).show();	
		}

		@Override
		protected Void doInBackground(String... params) {
			
			res=CallService.mailService(emailString, secretKeyString, "SendMail");			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if(res.equals("success")) {			
			Intent intent=new Intent(getBaseContext(),EncDecSMSActivity.class);
			//intent.putExtra("username",username);
			startActivity(intent);
			}
			else {
				Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}


}