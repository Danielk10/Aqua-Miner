package com.diamon.miner;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.diamon.miner.AquaMiner;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppCenter.start(getApplication(), "d2a7969c-85b6-43e8-9da6-797779ca5b10",Analytics.class, Crashes.class);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new AquaMiner(), config);
	}
}
