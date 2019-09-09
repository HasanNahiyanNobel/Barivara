package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.api.User;
import com.example.barivara.api.UserClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(getString(R.string.server_and_port))
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.build();
		UserClient userClient = retrofit.create(UserClient.class);
		Call<List<User>> userListCall = userClient.userAll();
		userListCall.enqueue(new Callback<List<User>>() {
			@Override
			public void onResponse(Call<List<User>> call, Response<List<User>> response) {
				List<User> userList = response.body();
				for(User user : userList){
					//Toast.makeText(FindAHouse.this, house.getElaka(), Toast.LENGTH_SHORT).show();
					//placeName.add(house.getElaka()+" ("+house.getUpazilla()+", "+house.getZilla()+")");
				}
			}
			@Override
			public void onFailure(Call<List<User>> call, Throwable t) {
				Toast.makeText(RegistrationActivity.this, "error :(", Toast.LENGTH_SHORT).show();
			}
		});

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
