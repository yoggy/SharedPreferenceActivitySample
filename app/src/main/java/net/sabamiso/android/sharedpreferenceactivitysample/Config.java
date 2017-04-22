//
// Config.java - How to use SharedPreference
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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Vector;

public class Config {
    // singleton
    private static Config singleton;

    // 必ず一番初めにこの関数を実行し、singletonを初期化すること
    public static Config init(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        singleton = new Config(prefs);
        return singleton;
    }

    public static Config getInstance() {
        return singleton;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    protected SharedPreferences shared_preferences;

    protected Config(SharedPreferences prefs) {
        this.shared_preferences = prefs;
    }

    public synchronized String [] keys() {
        Map<String, ?> allEntries = shared_preferences.getAll();

        Vector<String> v = new Vector<String>();
        for (Map.Entry<String, ?> e : allEntries.entrySet()) {
            v.add(e.getKey());
        }
        return v.toArray(new String[0]);
    }

    public synchronized void remove(String key) {
        SharedPreferences.Editor e = shared_preferences.edit();
        e.remove(key);
        e.commit();
    }

    public synchronized boolean contains(String key) {
        return shared_preferences.contains(key);
    }

    public synchronized boolean getBoolean(String key, boolean default_val) {
        return shared_preferences.getBoolean(key, default_val);
    }

    public synchronized void setBoolean(String key, boolean val) {
        SharedPreferences.Editor e = shared_preferences.edit();
        e.putBoolean(key, val);
        e.commit();
    }

    public synchronized int getInt(String key, int default_val) {
        if (!contains(key)) setInt(key, default_val);
        int val = Integer.parseInt(shared_preferences.getString(key, ""));
        return val;
    }

    public synchronized void setInt(String key, int val) {
        String val_str = Integer.toString(val);
        setString(key, val_str);
    }

    public synchronized float getFloat(String key, float default_val) {
        if (!contains(key)) setFloat(key, default_val);
        float val = Float.parseFloat(shared_preferences.getString(key, ""));
        return val;
    }

    public synchronized void setFloat(String key, float val) {
        String val_str = Float.toString(val);
        setString(key, val_str);
    }

    public synchronized String getString(String key, String default_val) {
        return shared_preferences.getString(key, default_val);
    }

    public synchronized void setString(String key, String val) {
        SharedPreferences.Editor e = shared_preferences.edit();
        e.putString(key, val);
        e.commit();
    }

    public synchronized String [] getKeys() {
        Map<String, ?>	map = shared_preferences.getAll();

        String [] keys = new String[map.size()];
        int idx = 0;
        for (Map.Entry<String, ?> e : map.entrySet()) {
            keys[idx] = e.getKey();
        }
        return keys;
    }
}
