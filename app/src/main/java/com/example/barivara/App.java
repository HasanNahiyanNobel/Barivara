package com.example.barivara;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class App extends Application {
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/kalpurush.ttf")
				.setFontAttrId(R.attr.fontPath)
				.build()
		);
	}
}
