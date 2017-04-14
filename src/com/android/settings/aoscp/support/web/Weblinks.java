/*
 * Copyright (C) 2017 CypherOS
 * Copyright (C) 2017 ViperOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.aoscp.support.web;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference;

import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import java.util.ArrayList;
import java.util.List;

public class Weblinks extends SettingsPreferenceFragment {
    private static final String TAG = "Weblinks";
	
    Preference mGoogleUrl;
	Preference mGithubUrl;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.software_weblinks);
		
        final Activity activity = getActivity();
        final ContentResolver resolver = activity.getContentResolver();
		
        mGoogleUrl = findPreference("viper_plus");
		mGithubUrl = findPreference("github_source");
		
	}
	
	@Override
    protected int getMetricsCategory() {
        return MetricsEvent.VENOM;
    }
	
	@Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mGoogleUrl) {
            launchUrl("https://plus.google.com/communities/112731559207528820150");
		} else if (preference == mGithubUrl) {
            launchUrl("https://github.com/Viper0S");
		} 
        return super.onPreferenceTreeClick(preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(intent);
    }
}