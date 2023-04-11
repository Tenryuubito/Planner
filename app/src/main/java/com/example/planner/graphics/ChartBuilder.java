package com.example.planner.graphics;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.planner.R;
import com.example.planner.db.entity.Payment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChartBuilder implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    private static SeekBar seekBarX;
    private static SeekBar seekBarY;
    private static TextView tvX;
    private static TextView tvY;
    private static LineChart chart;
    private static List<Payment> dataInput;
    public static LinearLayout getLineChart(@NonNull LayoutInflater inflater, ViewGroup container, List<Payment> data) {

        View binding = inflater.inflate(R.layout.template_linechart, container, false);
        LinearLayout LinearLayout_Frame = binding.findViewById(R.id.Template_LinearLayout_Frame);

        dataInput = data;

        chart = binding.findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setAutoScaleMinMaxEnabled(true);

        tvX = binding.findViewById(R.id.tvXMax);
        tvY = binding.findViewById(R.id.tvYMax);

        seekBarX = binding.findViewById(R.id.seekBar1);
        seekBarY = binding.findViewById(R.id.seekBar2);

        XAxis xAxis;
        xAxis = chart.getXAxis();
        xAxis.disableAxisLineDashedLine();
        xAxis.disableGridDashedLine();

        YAxis yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        yAxis.enableGridDashedLine(100f, 10f, 10f);

        yAxis.setDrawLimitLinesBehindData(true);

        seekBarY.setProgress(180);
        Pair<Integer, Integer> ySize = setData(chart, data);

        yAxis.setAxisMinimum(ySize.first);
        yAxis.setAxisMaximum(ySize.second);

        chart.animateX(1000);

        ((ViewGroup)LinearLayout_Frame.getParent()).removeView(LinearLayout_Frame);

        return LinearLayout_Frame;
    }

    private static Pair<Integer, Integer> setData(LineChart chart, List<Payment> dataInput) {

        ArrayList<Entry> values = new ArrayList<>();
        String multipleDate = "";
        float min = 0f;
        float max = 0f;

        // Set data for the Chart {
        Collections.sort(dataInput);
        Month month = LocalDate.now().getMonth();

        for (Payment payment : dataInput) {
            if (!payment.date.equals("") && payment.date.split("\\.")[1].equals(month.getValue() - 1 < 10 ? "0" + (month.getValue() - 1) : "" + (month.getValue() - 1))) {
                float amountInput = payment.isPayment ? -payment.amount : payment.amount;

                if (multipleDate.equals(payment.date)) {
                    amountInput += values.get(values.size() - 1).getY();
                    values.get(values.size() - 1).setY(amountInput);
                } else {
                    values.add(new Entry(Integer.parseInt(payment.date.split("\\.")[0]), amountInput));
                }

                multipleDate = payment.date;
                min = Math.min(min, amountInput);
                max = Math.max(max, amountInput);
            }
        }
        // } end of set data for the chart

        LineDataSet set1;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {

            set1 = new LineDataSet(values, "");

            set1.disableDashedLine();

            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.BLACK);

            set1.setValueTextSize(10f);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);

            chart.setData(data);
        }
        return new Pair<>((int) min - 5, (int) max + 5);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        tvX.setText(String.valueOf(seekBarX.getProgress()));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        setData(chart, dataInput);

        chart.invalidate();
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());

        chart.centerViewToAnimated(e.getX(), e.getY(), chart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 500);
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
