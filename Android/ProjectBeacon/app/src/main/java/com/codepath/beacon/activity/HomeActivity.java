package com.codepath.beacon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.beacon.R;
import com.crittercism.app.Crittercism;
import com.parse.ParseUser;


public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crittercism.initialize(getApplicationContext(), "53d465b0d478bc25cc000001");
		setContentView(R.layout.activity_home);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
		  handleSuccessfulLogin();
		}
		
	}

	protected void handleSuccessfulLogin() {
		//Toast.makeText(getApplicationContext(), "Hooray..the user can login", Toast.LENGTH_SHORT).show();
		Intent scanIntent = new Intent(this, MyRecipeActivity.class);
		startActivity(scanIntent);	    
	}
	
	public void onSignin(View view) {
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}
	
	public void onSignup(View view) {
		Intent i = new Intent(this, SignUpActivity.class);
		startActivity(i);
	}
}
