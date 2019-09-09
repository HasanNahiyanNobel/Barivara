package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
	EditText nameEditText, emailEditText, passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
	}

	public void register(View view) {
		nameEditText = findViewById(R.id.editText5);
		emailEditText = findViewById(R.id.editText7);
		passwordEditText = findViewById(R.id.editText8);
		Toast.makeText(RegistrationActivity.this, nameEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(RegistrationActivity.this, emailEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(RegistrationActivity.this, passwordEditText.getText(), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
