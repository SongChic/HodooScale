package com.animal.scale.hodoo.activity.home.weight.statistics;

import android.content.Context;
import android.graphics.Color;

import com.animal.scale.hodoo.R;
import com.animal.scale.hodoo.activity.home.weight.statistics.chart.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;

public class WeightStatisticsModel {

    Context context;

    protected String[] mDays = new String[] {
            "월", "화", "수", "목", "금", "토", "일"
    };

    protected String[] mWeeks = new String[] {
            "1째주", "2째주", "3째주", "4째주"
    };

    protected String[] mMonths = new String[] {
            "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"
    };
    protected String[] mYears = new String[] {
            "2012", "2013", "2014", "2015", "2016", "2017", "2018"
    };

    public void initLoadData(Context context) {
        this.context  = context;
    }

    public void setupChart(LineChart chart, LineData data, final String[] xValues) {

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);

        MyMarkerView mv  = new MyMarkerView(context, R.layout.mpchart_market_layout);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart
        chart.setBackgroundColor(Color.parseColor("#f7f7f7"));
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues[(int) value % xValues.length];
            }
        });
        chart.setData(data);
        chart.getLegend().setEnabled(false);
        //chart.animateX(2500);
        chart.invalidate();

        Highlight highlight = new Highlight((float) data.getEntryCount(), 0, -1);
        chart.highlightValue(highlight, false);
    }


    public LineData getData(ArrayList<Entry> yVals) {
        LineDataSet set1 = new LineDataSet(yVals, "DataSet");
        set1.setDrawValues(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.parseColor("#fc9596"));
        set1.setCircleColor(Color.parseColor("#fc9596"));
        set1.setHighLightColor(Color.parseColor("#fc9596"));
        return new LineData(set1);
    }

    public ArrayList<Entry> getDayData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            float val = (float) (Math.random() * 30) + 3;
            yVals.add(new Entry(i, val));
        }
        return yVals;
    }

    public ArrayList<Entry> getWeekData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < 4; i++) {
            float val = (float) (Math.random() * 30) + 3;
            yVals.add(new Entry(i, val));
        }
        return yVals;
    }

    public ArrayList<Entry> getMonthData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            float val = (float) (Math.random() * 30) + 3;
            yVals.add(new Entry(i, val));
        }
        return yVals;
    }

    public ArrayList<Entry> getYearData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            float val = (float) (Math.random() * 30) + 3;
            yVals.add(new Entry(i, val));
        }
        return yVals;
    }
}
