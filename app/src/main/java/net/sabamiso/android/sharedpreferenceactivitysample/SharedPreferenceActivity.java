//
// SharedPreferenceActivity.java - How to use PreferenceActivity & PreferenceFragment
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

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SharedPreferenceActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferenceFragment fragment = new SharedPreferenceFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }
}
