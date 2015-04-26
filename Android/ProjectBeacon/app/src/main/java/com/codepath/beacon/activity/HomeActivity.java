package com.codepath.beacon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codepath.beacon.R;
import com.codepath.beacon.events.AmplitudeEventTracker;
import com.codepath.beacon.events.EventName;
import com.codepath.beacon.events.EventTracker;
import com.crittercism.app.Crittercism;
import com.parse.ParseUser;


public class HomeActivity extends Activity {
    private EventTracker mEventTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crittercism.initialize(getApplicationContext(), "53d465b0d478bc25cc000001");
		setContentView(R.layout.activity_home);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
		  handleSuccessfulLogin();
		}
		mEventTracker = AmplitudeEventTracker.getInstance();
	}

    @Override
    protected void onResume() {
        super.onResume();
        mEventTracker.startSession();
        mEventTracker.track(EventName.HOME_PAGE_RENDER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEventTracker.endSession();
    }

    protected void handleSuccessfulLogin() {
		//Toast.makeText(getApplicationContext(), "Hooray..the user can login", Toast.LENGTH_SHORT).show();
		Intent scanIntent = new Intent(this, MyRecipeActivity.class);
		startActivity(scanIntent);	    
	}
	
	public void onSignin(View view) {
        mEventTracker.track(EventName.HOME_PAGE_LOGIN_CLICKED);
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}
	
	public void onSignup(View view) {
        mEventTracker.track(EventName.HOME_PAGE_SIGNUP_CLICKED);
		Intent i = new Intent(this, SignUpActivity.class);
		startActivity(i);
	}
}
