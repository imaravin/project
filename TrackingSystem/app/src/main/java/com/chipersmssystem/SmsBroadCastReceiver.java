package com.chipersmssystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;


/**
 * @author sentamilpandi.m
 *
 */

public class SmsBroadCastReceiver extends BroadcastReceiver {

 @Override
 public void onReceive(Context context, Intent intent) {

 Bundle bundle = intent.getExtras();

 Object[] object = (Object[]) bundle.get("pdus");
 SmsMessage sms[] = new SmsMessage[object.length]; 
 Intent in=new Intent(context,DisplaySMSActivity.class);
 in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 in.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 String msgContent = "";
 String originNum = "";
 StringBuffer sb = new StringBuffer();

 for (int i = 0; i < object.length; i++) {

 sms[i] = SmsMessage.createFromPdu((byte[]) object[i]);
 msgContent = sms[i].getDisplayMessageBody();
 originNum = sms[i].getDisplayOriginatingAddress();
 sb.append(msgContent);
 abortBroadcast();

 }
 
 in.putExtra("originNum", originNum);
 in.putExtra("msgContent", new String(sb));
 context.startActivity(in);
 
 }

}
