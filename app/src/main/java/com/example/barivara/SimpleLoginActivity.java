package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SimpleLoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_login);
	}

	public void signInOrRegisterClicked(View view) {
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
