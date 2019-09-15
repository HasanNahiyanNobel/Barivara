package com.example.barivara;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegistrationActivity extends AppCompatActivity {
	EditText nameEditText, emailEditText, passwordEditText;
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
	}

	public void register(View view) {
		nameEditText = findViewById(R.id.editText5);
		emailEditText = findViewById(R.id.editText7);
		passwordEditText = findViewById(R.id.editText8);
		/*Toast.makeText(this, nameEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, emailEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, passwordEditText.getText(), Toast.LENGTH_SHORT).show();*/
		Toast.makeText(this, getString(R.string.registration_successful_toast), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
