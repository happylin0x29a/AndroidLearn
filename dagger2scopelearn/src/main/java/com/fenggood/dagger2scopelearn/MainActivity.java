package com.fenggood.dagger2scopelearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fenggood.dagger2scopelearn.beans.User;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    User user1;
    @Inject
    User user2;
    private TextView tv1;
    private TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DaggerUserComponent.builder().build().inject(this); 不使用module提供
//        DaggerUserComponent.builder().userModule(new UserModule()).build().inject(this); 使用module提供
        MyApplication myApplication=(MyApplication)getApplication();
        myApplication.getUserComponentCustom().inject(this);//全局单例模式
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv1.setText(user1+"");
        tv2.setText(user2+"");
    }

    public void go(View view) {
        startActivity(new Intent(this,BActivity.class));
    }
}
