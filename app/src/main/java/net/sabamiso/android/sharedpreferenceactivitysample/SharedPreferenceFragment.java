//
// SharedPreferenceFragment.java - How to use PreferenceActivity & PreferenceFragment
//
// GitHub:
//     https://github.com/yoggy/SharedPreferenceActivitySample
//
// license:
//     Copyright (c) 2017 yoggy <yoggy0@gmail.com>
//     Released under the MIT license
//     http://opensource.org/licenses/mit-license.php;
//
package net.sabamiso.android.sharedpreferenceactivitysample;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import org.xmlpull.v1.XmlPullParser;

public class SharedPreferenceFragment  extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ここでXML(res/xml/preference.xml)に定義されている
        // defaultValueをSharedPreferenceに反映する
        PreferenceManager.setDefaultValues(getActivity(),
                R.xml.preference, false);

        addPreferencesFromResource(R.xml.preference);
        updatePreferenceSummarys();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if (p != null) {
            if (p instanceof CheckBoxPreference) {
                p.setSummary("current_value=" + (sharedPreferences.getBoolean(key, false) ? "true" : "false"));
            }
            else{
                p.setSummary("current_value=" + sharedPreferences.getString(key, ""));
            }
        }
    }

    // PreferenceFragmentを定義するXML(res/xml/preference.xml)を読み込んで、
    // 設定キー一覧を取得後、summaryに現在設定されている値を表示する
    public void updatePreferenceSummarys() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Resources res = this.getResources();
        XmlResourceParser parser = res.getXml(R.xml.preference);

        try {
            parser.next();
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType == XmlPullParser.START_TAG) {
                    for(int i = 0; i < parser.getAttributeCount(); i++) {
                        String name = parser.getAttributeName(i);
                        String val = parser.getAttributeValue(i);
                        if ("key".equals(name)) {
                            onSharedPreferenceChanged(prefs, val);
                        }
                    }
                }
                eventType = parser.next();
            }
        }
        catch(Exception e) {
            // nothing to do...
        }
    }
}

