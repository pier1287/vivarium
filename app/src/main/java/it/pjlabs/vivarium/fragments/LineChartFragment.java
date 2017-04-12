package it.pjlabs.vivarium.fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import it.pjlabs.vivarium.R;
import it.pjlabs.vivarium.data.entities.Measurement;

/**
 * Created by Pj on 19/11/16.
 */

public class LineChartFragment extends BaseFragment {

    @BindView(R.id.line_chart) LineChart mLineChart;
    private LineDataSet mLineDataSet;
    private LineData mLineData;

    public LineChartFragment() {}

    public static LineChartFragment newInstance() {
        LineChartFragment fragment = new LineChartFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        populateChart();
        return mInflatedView;
    }


    public void addMeasurement (Measurement measurement) {
        float value = measurement.getValue().floatValue();
        float date = measurement.getDateM().getTime();
        mLineDataSet.addEntry(new Entry(value,date,measurement));
        mLineData.notifyDataChanged();
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();

    }

    private void populateChart() {
        // no description text

        mLineChart.setDrawGridBackground(false);

        // no description text
        mLineChart.getDescription().setEnabled(false);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        // set an alternative background color
        // mLineChart.setBackgroundColor(Color.GRAY);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);

        //Da settare formato data
        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());


        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaximum(40f);
        leftAxis.setAxisMinimum(10f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        LimitLine maxLine = new LimitLine(32f);
        maxLine.enableDashedLine(20f,8f,0f);
        maxLine.setLineWidth(2f);
        maxLine.setTextColor(Color.RED);
        maxLine.setTextSize(10f);
        maxLine.setLineColor(Color.RED);
        maxLine.setLabel("Max Temperature");
        maxLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);

        LimitLine minLine = new LimitLine(22f);
        minLine.enableDashedLine(20f,8f,0f);
        minLine.setLineWidth(2f);
        minLine.setTextColor(Color.RED);
        minLine.setTextSize(10f);
        minLine.setLineColor(Color.RED);
        minLine.setLabel("Min Temperature");
        minLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);

        leftAxis.addLimitLine(maxLine);
        leftAxis.addLimitLine(minLine);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChart.getAxisRight().setEnabled(false);

        // add data
        initSetData(10, 10);

        mLineChart.animateX(1000);
    }

    private void initSetData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range)+22;
            values.add(new Entry(i, val));
        }

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            mLineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            mLineDataSet.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            mLineDataSet = new LineDataSet(values, getString(R.string.label_temperature));

            mLineDataSet.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            mLineDataSet.setCircleColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
            mLineDataSet.setLineWidth(3f);
            mLineDataSet.setCircleRadius(3.5f);
            mLineDataSet.setValueTextSize(10f);
            mLineDataSet.setValueTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(mLineDataSet); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mLineChart.setData(data);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_line_chart;
    }


}
