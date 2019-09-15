package com.example.barivara;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * The following class and functions are taken from http://hmkcode.com/android-send-json-data-to-server/
 * 1. private class HTTPAsyncTask extends AsyncTask<String, Void, String>
 * 2. private String HttpPost(String myUrl) throws IOException, JSONException
 * 3. private JSONObject buildJsonObject() throws JSONException
 * 4. private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException
 */

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

		new HTTPAsyncTask().execute(getString(R.string.server_and_port)+getString(R.string.server_user_data));

		Toast.makeText(this, getString(R.string.registration_successful_toast), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			// params comes from the execute() call: params[0] is the url.
			try {
				try {
					return HttpPost(urls[0]);
				} catch (JSONException e) {
					e.printStackTrace();
					return "Error!";
				}
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}
		/*// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			tvResult.setText(result);
		}*/
	}

	private String HttpPost(String myUrl) throws IOException, JSONException {
		URL url = new URL(myUrl);

		// 1. create HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		// 2. build JSON object
		JSONObject jsonObject = buildJsonObject();

		// 3. add JSON content to POST request body
		setPostRequestContent(conn, jsonObject);

		// 4. make POST request to the given URL
		conn.connect();

		// 5. return response message
		return conn.getResponseMessage()+"";
	}

	private JSONObject buildJsonObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("name", nameEditText.getText().toString());
		jsonObject.accumulate("email",  emailEditText.getText().toString());
		jsonObject.accumulate("password",  passwordEditText.getText().toString());
		return jsonObject;
	}

	private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(jsonObject.toString());
		Log.i(MainActivity.class.toString(), jsonObject.toString());
		writer.flush();
		writer.close();
		os.close();
	}
}
