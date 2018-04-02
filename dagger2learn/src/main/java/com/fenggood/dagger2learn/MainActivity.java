package com.fenggood.dagger2learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fenggood.dagger2learn.beans.Person;
import com.fenggood.dagger2learn.component.DaggerFoodComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    TextView info;
    @Inject
    Person person; //不能写私有的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info=findViewById(R.id.tv_info);
        DaggerFoodComponent.builder().build().inject(this);
        info.setText(person.eat());
    }
}
