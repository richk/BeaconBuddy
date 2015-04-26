package com.codepath.beacon.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplitude.api.Amplitude;
import com.codepath.beacon.R;
import com.codepath.beacon.events.AmplitudeEventTracker;
import com.codepath.beacon.events.EventName;
import com.codepath.beacon.events.EventTracker;
import com.codepath.beacon.fragments.RecipeAlertDialog;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {
	private static final String LOG_TAG = SignUpActivity.class.getSimpleName();
	
	private EditText etUserName;
	private EditText etPwd;
    private EventTracker mEventTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		etUserName = (EditText) findViewById(R.id.etSignUpEmail);
		etPwd = (EditText) findViewById(R.id.etSignUpPwd);
		if (savedInstanceState != null) {
			String uname = savedInstanceState.getString("unameField");
			String pwd = savedInstanceState.getString("pwdField");
			etUserName.setText(uname);
			etPwd.setText(pwd);
		}
        mEventTracker = AmplitudeEventTracker.getInstance();
	}

    @Override
    protected void onResume() {
        super.onResume();
        mEventTracker.startSession();
        mEventTracker.track(EventName.SIGNUP_PAGE_RENDER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEventTracker.endSession();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      // Respond to the action bar's Up/Home button
	      case android.R.id.home:
	        Intent intent = NavUtils.getParentActivityIntent(this); 
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP); 
	        NavUtils.navigateUpTo(this, intent);
	        return true;        
	    }

	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String uname = etUserName.getText().toString();
		String pwd = etPwd.getText().toString();
		if (!uname.isEmpty()) {
		    outState.putString("unameField", uname);
		}
		if (!pwd.isEmpty()) {
			outState.putString("pwdField", pwd);
		}
	}

	
	public void onSignup(View view) {
		Toast.makeText(this, "Signing in", Toast.LENGTH_SHORT).show();
        mEventTracker.track(EventName.SIGNUP_PAGE_SIGNUP_CLICKED);
		String username = etUserName.getText().toString();
		String pwd = etPwd.getText().toString();
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(pwd);
		if (!isNetworkAvailable()) {
			showNoNetwork();
			return;
		}
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException exception) {
				if (exception == null) {
					handleSuccessfulSignup();
				} else {
					handleUnsuccessfulSignup(exception);
				}
			}
		});
	}

	protected void handleUnsuccessfulSignup(ParseException exception) {
		Toast.makeText(this, "Unsuccessful signup attempt:" + exception.getMessage(), Toast.LENGTH_SHORT).show();
	}

	protected void handleSuccessfulSignup() {
		Intent scanIntent = new Intent(this, MyRecipeActivity.class);
		startActivity(scanIntent);
	}
	
	  public void showNoNetwork() {
		  RecipeAlertDialog alertDialog = new RecipeAlertDialog();
		  Bundle args = new Bundle();
		  args.putString("message", getResources().getString(R.string.network_error_message));
		  alertDialog.setArguments(args);
		  alertDialog.show(getFragmentManager(), null);
		  return;
	  }
	  
	  public boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
		}
}
