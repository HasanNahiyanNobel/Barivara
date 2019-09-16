package com.example.barivara;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DefaultFontApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath(getString(R.string.default_font_path))
				.setFontAttrId(R.attr.fontPath)
				.build()
		);
	}
}
