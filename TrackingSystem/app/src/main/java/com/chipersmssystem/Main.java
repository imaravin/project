package com.chipersmssystem;



import com.example.trackingsystem.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author sentamilpandi.m
 *
 */

public class Main extends Activity {
	
	EditText uname,pass;
	Button signin,signup;
	ProgressBar mainpb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		uname=(EditText)findViewById(R.id.uname);
		pass=(EditText)findViewById(R.id.pass);
		mainpb=(ProgressBar)findViewById(R.id.pb1);
		signin=(Button)findViewById(R.id.signin);
		signup=(Button)findViewById(R.id.signup);
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActiveTask task=new ActiveTask();
				task.execute();
			}
		});
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1=new Intent(getBaseContext(), Register.class);				
				startActivity(intent1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class ActiveTask extends AsyncTask<String,Void,Void> {
		String username=uname.getText().toString();
		String password=pass.getText().toString();
		String res=null;
		@Override
		protected void onPreExecute() {
			mainpb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=CallService.loginService(username, password, "signin");			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if(res.equals("success")) {			
			Intent intent=new Intent(getBaseContext(),EncDecSMSActivity.class);
			intent.putExtra("username",username);
			startActivity(intent);
			}
			else {
				Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
			}
			mainpb.setVisibility(View.INVISIBLE);
		}
		
	}

}
