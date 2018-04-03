package com.fenggood.dagger2scopelearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fenggood.dagger2scopelearn.beans.User;

import javax.inject.Inject;

public class BActivity extends AppCompatActivity {
    @Inject
    User user1;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        MyApplication myApplication = (MyApplication) getApplication();
        myApplication.getUserComponentCustom().inject(this);
        tv = findViewById(R.id.tvb1);
        tv.setText(user1 + "");
    }
}
