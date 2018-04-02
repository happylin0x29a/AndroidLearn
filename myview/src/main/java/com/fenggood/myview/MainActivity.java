package com.fenggood.myview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private LineChart mChart;
    private ArrayList<Entry> mValues;
    private CurveView curveView;
    private DashBoardView dashBoardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dashBoardView=findViewById(R.id.dbv_data);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dashBoardView.setCurrentValue((float) (50-Math.random()*100));
                    }
                });
            }
        },new Date(),3000);

//        mChart=findViewById(R.id.view_lineChart);
//        setLineChart();
//        setCurveView();
//        setLineView();
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
//    private void setLineView() {
//        lineView = findViewById(R.id.view_line);
//        float[] data = {10, 20, 30, 50, 60, 70, 100};
//        float[] data1 = {15, 25, 35, 53, 61, 72, 88};
//        float[] data3 = {16, 35, 75, 43, 61, 62, 28};
//        float[] data4 = {26, 35, 55, 63, 81, 62, 28};
//        float[] data5 = {16, 45, 75, 73, 61, 92, 28};
//        String[] xValue = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
//        lineView.setXNames(xValue);
//        lineView.addLine("林峰1", data, Color.RED);
//        lineView.addLine("林峰2", data1, Color.BLUE);
//        lineView.addLine("林峰3", data3, Color.GRAY);
////        lineView.addLine("林峰4",data4, Color.DKGRAY);
//        lineView.addLine("林峰5", data5, Color.YELLOW);
//    }
//    private void setCurveView(){
//        curveView=findViewById(R.id.view_cure);
//        curveView.addXValue("1");
//        curveView.addXValue("2");
//        curveView.addXValue("3");
//        curveView.addXValue("4");
//        curveView.addXValue("5");
//        curveView.addXValue("6");
//        curveView.addXValue("7");
//        float[] data = {10, 20, 30, 50, 60, 70, 100};
//        float[] data1 = {15, 25, 35, 53, 61, 72, 88};
//        float[] data3 = {16, 35, 75, 43, 61, 62, 28};
//        float[] data4 = {26, 35, 55, 63, 81, 62, 28};
//        float[] data5 = {16, 45, 75, 73, 61, 92, 28};
//        curveView.addLine("林峰1", data, Color.RED);
//        curveView.addLine("林峰2", data1, Color.BLUE);
//        curveView.addLine("林峰3", data3, Color.GRAY);
//        curveView.addLine("林峰4",data4, Color.DKGRAY);
//        curveView.addLine("林峰5", data5, Color.YELLOW);
//    }
    private void setLineChart(){
        //此属性设置之后,点击图标中的某个点网格会自动将此点移动到屏幕中心 不设置则没反应
        mChart.setOnChartValueSelectedListener(this);
        ////是否禁止绘制图表边框的线
        mChart.setDrawBorders(false);
        //设置chart边框线颜色
        mChart.setBorderColor(Color.RED);
        //设置chart边框线宽度
        mChart.setBorderWidth(1f);
        //设置chart是否可以触摸
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        //设置是否可以拖拽  
        mChart.setDragEnabled(true);
        //设置是否可以缩放 x和y，默认true
        mChart.setScaleEnabled(true);
        //设置是否可以通过双击屏幕放大图表。默认是true
        mChart.setDoubleTapToZoomEnabled(true);
        //是否启用网格背景
        mChart.setDrawGridBackground(false);
        //设置chart动画 x轴y轴都有动画
        //mChart.animateXY(2000, 2000);
        // false代表缩放时X与Y轴分开缩放,true代表同时缩放
        mChart.setPinchZoom(true);
        // set an alternative background color
        //图表背景颜色的设置
        mChart.setBackgroundColor(Color.LTGRAY);
        //图表进入的动画时间 从左到右
        mChart.animateX(1000);
        //描述信息
//        Description description = new Description();
//        description.setText("描述信息相关内容");
//        //设置描述信息
//        mChart.setDescription(null);
        //设置没有数据时显示的文本
        mChart.setNoDataText("没有数据 啊啊啊");
        XAxis xAxis=mChart.getXAxis();
        xAxis.setDrawAxisLine(true);//设置是否绘制轴线 默认是true
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawGridLines(true);//绘制标签 指X轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(12f);//设置文字大小
        xAxis.setAxisMinimum(0f);//设置x轴的最小值 //`
        xAxis.setAxisMaximum(31f);//设置最大值 //
        xAxis.setLabelCount(10);  //设置X轴的显示个数
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setAxisLineColor(Color.BLACK);//设置x轴线颜色
        xAxis.setAxisLineWidth(0.5f);//设置x轴线宽度
        YAxis leftAxis = mChart.getAxisLeft();
        YAxis axisRight = mChart.getAxisRight();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
        leftAxis.setGranularity(20f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#7189a9"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setTextSize(12f);            //设置Y轴刻度字体
        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(0.5f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setMinWidth(0f);
        leftAxis.setMaxWidth(200f);
        leftAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        Legend l = mChart.getLegend();//图例
//        l.setEnabled(false);   //是否使用 图例
        l.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        setData();
    }
    private void setData(){
        mValues=new ArrayList<>();
        mValues.add(new Entry(0, 10));
        mValues.add(new Entry(1, 15));
        mValues.add(new Entry(2, 25));
        mValues.add(new Entry(3, 19));
        mValues.add(new Entry(4, 25));
        mValues.add(new Entry(5, 16));
        mValues.add(new Entry(6, 40));
        mValues.add(new Entry(7, 27));
        LineDataSet set1;//这代表一条线
        if(mChart.getData()!=null&&mChart.getData().getDataSetCount()>0){
            //获取数据1
            set1= (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(mValues);
            //刷新数据
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }else{
            set1=new LineDataSet(mValues,"测试数据1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.parseColor("#AAFFFFFF"));
            set1.setHighLightColor(Color.WHITE);//设置点击交点后显示交高亮线的颜色
            set1.setHighlightEnabled(true);//是否使用点击高亮线
//            set1.setDrawCircles(true);
            set1.setValueTextColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽
            set1.setCircleRadius(2f);//设置焦点圆心的大小
            set1.setHighlightLineWidth(0.5f);//设置点击交点后显示高亮线宽
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setValueTextSize(12f);//设置显示值的文字大小
            set1.setDrawFilled(true);//设置使用 范围背景填充
            set1.setDrawValues(true);
        }
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(set1);//
        LineData data=new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();
    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(MainActivity.this,e.getY()+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }
}
