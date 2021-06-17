package com.example.accout2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ChartActivity extends AppCompatActivity {

    private MyData myData;

    private ArrayList<Account> dataList;

    PieChart pieChart1;

    PieChart pieChart3;

    BarChart barChart2;

    BarChart barChart4;

    ArrayList<PieEntry> pieEntries = new ArrayList<>();
    ArrayList<PieEntry> pieEntries2 = new ArrayList<>();

    ArrayList<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        myData = (MyData)getApplication();
        dataList = myData.getDataList();
        TitleLayout titleLayout = (TitleLayout) findViewById(R.id.title_chart);
        titleLayout.getTextView().setText("数据图表");
        setAlldata();
        inChart1Data();
        showSolidpieChart1(pieEntries, colors);
        inChart3Data();
        showSolidpieChart3(pieEntries2, colors);
        inBarchart2();
        inBarChart4();
    }

    public void showSolidpieChart1(List<PieEntry> yvals, List<Integer> colors) {
        //数据集合
        PieDataSet dataset = new PieDataSet(yvals, "");
        //填充每个区域的颜色
        dataset.setColors(colors);
        //是否在图上显示数值
        dataset.setDrawValues(true);
//        文字的大小
        dataset.setValueTextSize(8);
//        文字的颜色
        dataset.setValueTextColor(Color.RED);
//        文字的样式
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      当值位置为外边线时，表示线的前半段长度。
        dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
        dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
        dataset.setValueLinePart1OffsetPercentage(80f);
        // 当值位置为外边线时，表示线的颜色。
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置每条之前的间隙
        dataset.setSliceSpace(2f);

        //设置饼状Item被选中时变化的距离
        dataset.setSelectionShift(5f);
        //填充数据
        PieData pieData = new PieData(dataset);
//        格式化显示的数据为%百分比
        pieData.setValueFormatter(new PercentFormatter());
        pieChart1.setUsePercentValues(true);
        pieChart1.setEntryLabelTextSize(8);
        pieChart1.getDescription().setEnabled(false);
        pieChart1.setExtraOffsets(0, 0, 40, 0);
//        显示试图
        pieChart1.setData(pieData);
    }

    public void showSolidpieChart3(List<PieEntry> yvals, List<Integer> colors) {
        //数据集合
        PieDataSet dataset = new PieDataSet(yvals, "");
        //填充每个区域的颜色
        dataset.setColors(colors);
        //是否在图上显示数值
        dataset.setDrawValues(true);
//        文字的大小
        dataset.setValueTextSize(8);
//        文字的颜色
        dataset.setValueTextColor(Color.RED);
//        文字的样式
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      当值位置为外边线时，表示线的前半段长度。
        dataset.setValueLinePart1Length(0.4f);
//      当值位置为外边线时，表示线的后半段长度。
        dataset.setValueLinePart2Length(0.4f);
//      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
        dataset.setValueLinePart1OffsetPercentage(80f);
        // 当值位置为外边线时，表示线的颜色。
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
//        设置Y值的位置是在圆内还是圆外
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        设置每条之前的间隙
        dataset.setSliceSpace(2f);

        //设置饼状Item被选中时变化的距离
        dataset.setSelectionShift(5f);
        //填充数据
        PieData pieData = new PieData(dataset);
//        格式化显示的数据为%百分比
        pieData.setValueFormatter(new PercentFormatter());
        pieChart3.setUsePercentValues(true);
        pieChart3.setEntryLabelTextSize(8);
        pieChart3.getDescription().setEnabled(false);
        pieChart3.setExtraOffsets(0, 0, 40, 0);
//        显示试图
        pieChart3.setData(pieData);
    }

    private void inChart1Data() {
        pieChart1 = (PieChart) findViewById(R.id.chart1);
        ArrayList<Account> list = this.dataList;
        int cost1 = 0;
        int cost2 = 0;
        int cost3 = 0;
        int cost4 = 0;
        int cost5 = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFlag() == false && list.get(i).getDate().getMonth() == new Date(System.currentTimeMillis()).getMonth() &&
                    list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                switch (list.get(i).getMonryType()) {
                    case 1:
                        cost1 += list.get(i).getMoney();
                        break;
                    case 2:
                        cost2 += list.get(i).getMoney();
                        break;
                    case 3:
                        cost3 += list.get(i).getMoney();
                        break;
                    case 4:
                        cost4 += list.get(i).getMoney();
                        break;
                    case 5:
                        cost5 += list.get(i).getMoney();
                        break;
                    default:
                        break;
                }
            }
        }
        pieEntries.add(new PieEntry(cost1, "其他"));
        pieEntries.add(new PieEntry(cost2, "零食饮料"));
        pieEntries.add(new PieEntry(cost3, "学校饮食"));
        pieEntries.add(new PieEntry(cost4, "外出娱乐"));
        pieEntries.add(new PieEntry(cost5, "学习用品"));
        colors.add(Color.parseColor("#48D1CC"));
        colors.add(Color.parseColor("#00FF7F"));
        colors.add(Color.parseColor("#F0E68C"));
        colors.add(Color.parseColor("#FFA07A"));
        colors.add(Color.parseColor("#DDA0DD"));
    }

    private void inChart3Data() {
        pieChart3 = (PieChart) findViewById(R.id.chart3);
        ArrayList<Account> list =this.dataList;
        int cost1 = 0;
        int cost2 = 0;
        int cost3 = 0;
        int cost4 = 0;
        int cost5 = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFlag() == false && list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                switch (list.get(i).getMonryType()) {
                    case 1:
                        cost1 += list.get(i).getMoney();
                        break;
                    case 2:
                        cost2 += list.get(i).getMoney();
                        break;
                    case 3:
                        cost3 += list.get(i).getMoney();
                        break;
                    case 4:
                        cost4 += list.get(i).getMoney();
                        break;
                    case 5:
                        cost5 += list.get(i).getMoney();
                        break;
                    default:
                        break;
                }
            }
        }
        pieEntries2.add(new PieEntry(cost1, "其他"));
        pieEntries2.add(new PieEntry(cost2, "零食饮料"));
        pieEntries2.add(new PieEntry(cost3, "学校饮食"));
        pieEntries2.add(new PieEntry(cost4, "外出娱乐"));
        pieEntries2.add(new PieEntry(cost5, "学习用品"));
        colors.add(Color.parseColor("#48D1CC"));
        colors.add(Color.parseColor("#00FF7F"));
        colors.add(Color.parseColor("#F0E68C"));
        colors.add(Color.parseColor("#FFA07A"));
        colors.add(Color.parseColor("#DDA0DD"));
    }

    private void setAlldata() {
        Alldata alldata = (Alldata) findViewById(R.id.alldata);
        float mc = 0;
        float me = 0;
        float yc = 0;
        float ye = 0;
        ArrayList<Account> list =this.dataList;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFlag() == false && list.get(i).getDate().getMonth() == new Date(System.currentTimeMillis()).getMonth() &&
                    list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                mc += list.get(i).getMoney();
            }
            if (list.get(i).getFlag() == true && list.get(i).getDate().getMonth() == new Date(System.currentTimeMillis()).getMonth() &&
                    list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                me += list.get(i).getMoney();
            }
            if (list.get(i).getFlag() == false && list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                yc += list.get(i).getMoney();
            }
            if (list.get(i).getFlag() == true && list.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                ye += list.get(i).getMoney();
            }
        }
        alldata.getMonthcost().setText(new DecimalFormat("00.00").format(mc));
        alldata.getMonthearn().setText(new DecimalFormat("00.00").format(me));
        alldata.getYearcost().setText(new DecimalFormat("00.00").format(yc));
        alldata.getYearearn().setText(new DecimalFormat("00.00").format(ye));

    }

    private void inBarchart2() {
        barChart2 = (BarChart) findViewById(R.id.chart2);
        barChart2.getDescription().setEnabled(false); // 不显示描述
        setBarChart2Axis(); // 设置坐标轴
        setBarChart2Data();  // 设置数据
    }

    private void setBarChart2Axis() {
        // 设置x轴
        XAxis xAxis = barChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(5);  // 设置x轴上的标签个数
        xAxis.setTextSize(1f); // x轴上标签的大小
        final String labelName[] = {"其他", "零食饮料", "学校饮食", "外出娱乐", "学习用品"};
        // 设置x轴显示的值的格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ((int) value < labelName.length) {
                    return labelName[(int) value];
                } else {
                    return "";
                }
            }
        });
        xAxis.setYOffset(15); // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = barChart2.getAxisRight();
        yAxis_right.setAxisMaximum(2000f);  // 设置y轴的最大值
        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = barChart2.getAxisLeft();
        yAxis_left.setAxisMaximum(2000f);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setTextSize(2f); // 设置y轴的标签大小
    }

    private void setBarChart2Data() {
        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        ArrayList<Account> list1 = this.dataList;
        int cost1 = 0;
        int cost2 = 0;
        int cost3 = 0;
        int cost4 = 0;
        int cost5 = 0;
        for(int i = 0 ; i<list1.size();i++){
            if (list1.get(i).getFlag() == false && list1.get(i).getDate().getMonth() == new Date(System.currentTimeMillis()).getMonth() &&
                    list1.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                switch (list1.get(i).getMonryType()) {
                    case 1:
                        cost1 += list1.get(i).getMoney();
                        break;
                    case 2:
                        cost2 += list1.get(i).getMoney();
                        break;
                    case 3:
                        cost3 += list1.get(i).getMoney();
                        break;
                    case 4:
                        cost4 += list1.get(i).getMoney();
                        break;
                    case 5:
                        cost5 += list1.get(i).getMoney();
                        break;
                    default:
                        break;
                }
            }
        }
        barEntries1.add(new BarEntry(0, cost1));
        barEntries1.add(new BarEntry(1, cost2));
        barEntries1.add(new BarEntry(2, cost3));
        barEntries1.add(new BarEntry(3, cost4));
        barEntries1.add(new BarEntry(4, cost5));
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setValueTextColor(Color.parseColor("#000000")); // 值的颜色
        barDataSet1.setValueTextSize(10f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#FF1493")); // 柱子的颜色
        barDataSet1.setLabel("金额"); // 设置标签之后，图例的内容默认会以设置的标签显示
        // 设置柱子上数据显示的格式
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                // 此处的value默认保存一位小数
                return value+"";
            }
        });

        sets.add(barDataSet1);
        BarData barData = new BarData(sets);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart2.setData(barData);
    }

    private void inBarChart4(){
        barChart4 = (BarChart) findViewById(R.id.chart4);
        barChart4.getDescription().setEnabled(false); // 不显示描述
        setBarChart4Axis(); // 设置坐标轴
        setBarChart4Data();  // 设置数据
    }

    private void setBarChart4Axis(){
        // 设置x轴
        XAxis xAxis = barChart4.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(5);  // 设置x轴上的标签个数
        xAxis.setTextSize(1f); // x轴上标签的大小
        final String labelName[] = {"其他", "零食饮料", "学校饮食", "外出娱乐", "学习用品"};
        // 设置x轴显示的值的格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ((int) value < labelName.length) {
                    return labelName[(int) value];
                } else {
                    return "";
                }
            }
        });
        xAxis.setYOffset(15); // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = barChart4.getAxisRight();
        yAxis_right.setAxisMaximum(20000f);  // 设置y轴的最大值
        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = barChart4.getAxisLeft();
        yAxis_left.setAxisMaximum(20000f);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setTextSize(2f); // 设置y轴的标签大小
    }

    private void  setBarChart4Data(){
        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        ArrayList<Account> list1 = this.dataList;
        int cost1 = 0;
        int cost2 = 0;
        int cost3 = 0;
        int cost4 = 0;
        int cost5 = 0;
        for(int i = 0 ; i<list1.size();i++){
            if (list1.get(i).getFlag() == false && list1.get(i).getDate().getYear() == new Date(System.currentTimeMillis()).getYear()) {
                switch (list1.get(i).getMonryType()) {
                    case 1:
                        cost1 += list1.get(i).getMoney();
                        break;
                    case 2:
                        cost2 += list1.get(i).getMoney();
                        break;
                    case 3:
                        cost3 += list1.get(i).getMoney();
                        break;
                    case 4:
                        cost4 += list1.get(i).getMoney();
                        break;
                    case 5:
                        cost5 += list1.get(i).getMoney();
                        break;
                    default:
                        break;
                }
            }
        }
        barEntries1.add(new BarEntry(0, cost1));
        barEntries1.add(new BarEntry(1, cost2));
        barEntries1.add(new BarEntry(2, cost3));
        barEntries1.add(new BarEntry(3, cost4));
        barEntries1.add(new BarEntry(4, cost5));
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setValueTextColor(Color.parseColor("#000000")); // 值的颜色
        barDataSet1.setValueTextSize(10f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#FF8C00")); // 柱子的颜色
        barDataSet1.setLabel("金额"); // 设置标签之后，图例的内容默认会以设置的标签显示
        // 设置柱子上数据显示的格式
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                // 此处的value默认保存一位小数
                return value+"";
            }
        });

        sets.add(barDataSet1);
        BarData barData = new BarData(sets);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart4.setData(barData);
    }

}



