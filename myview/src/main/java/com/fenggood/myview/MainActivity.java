package com.fenggood.myview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LineView lineView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineView=findViewById(R.id.view_line);
        setLineView();
//        setPieChartView();
    }
//    private void  initCheck(){
//        checkBox1=findViewById(R.id.ck_1);
//        checkBox1.setOnCheckedChangeListener(this);
//        checkBox2=findViewById(R.id.ck_2);
//        checkBox2.setOnCheckedChangeListener(this);
//        bezierView2=findViewById(R.id.view_b2);
//        checkBox1.setChecked(true);
//    }
//    private void setPieChartView(){
//        PieChartView pieChartView=findViewById(R.id.view_pie);
//        ArrayList<PieChartView.PieData> list=new ArrayList<>();
//        for (int i=1;i<=6;i++){
//            list.add(new PieChartView.PieData(i+"林峰",i*5));
//        }
//        pieChartView.setData(list);
//    }
    private void setLineView(){
        float[] data={10,20,30,50,60,70,100};
        float[] data1={15,25,35,53,61,72,88};
        float[] data3={16,35,75,43,61,62,28};
        float[] data4={26,35,55,63,81,62,28};
        float[] data5={16,45,75,73,61,92,28};
        String[] xValue={"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
        lineView.setXNames(xValue);
        lineView.addLine("林峰1",data, Color.RED);
        lineView.addLine("林峰2",data1, Color.BLUE);
        lineView.addLine("林峰3",data3, Color.GRAY);
        lineView.addLine("林峰4",data4, Color.DKGRAY);
        lineView.addLine("林峰5",data5, Color.YELLOW);
    }
}
