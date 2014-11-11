package com.example.ti.ble.sensortag;

import java.util.Locale;

import com.example.ti.ble.sensortag.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferencesFragment extends PreferenceFragment  {
  
  private static final String TAG = "PreferencesFragment";
  private PreferencesListener preferencesListener;

  @Override
  public void onCreate(Bundle savedInstanceState) {
  	Log.i(TAG,"created");
    super.onCreate(savedInstanceState);
    if (DeviceActivity.getInstance().isSensorTag2())
    	addPreferencesFromResource(R.xml.preferences2);
    else
    	addPreferencesFromResource(R.xml.preferences);
    	
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    preferencesListener = new PreferencesListener(getActivity(), prefs, this);
    prefs.registerOnSharedPreferenceChangeListener(preferencesListener);
  }

  
  public boolean isEnabledByPrefs(final Sensor sensor) {
    String preferenceKeyString = "pref_" + sensor.name().toLowerCase(Locale.ENGLISH) + "_on";

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

    if (!prefs.contains(preferenceKeyString)) {
      //throw new RuntimeException("Programmer error, could not find preference with key " + preferenceKeyString);
    	return false;
    }

    return prefs.getBoolean(preferenceKeyString, true);
  }
}
