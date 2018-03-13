package com.fenggood.preferenceactivity;

import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(hasHeaders()){
            Button button=new Button(this);
            button.setText("Exit");
            setListFooter(button);
        }
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.headers,target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        // TODO: 2018/3/2 判断是否是有效的fragment
        Log.i("fragmentName:",fragmentName);
        return true;//是有效的fragment返回true
    }

    public static class Prefs1Fragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_fragment1);
            String name=((EditTextPreference)findPreference("name")).getText();
            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            
        }

    }
}
