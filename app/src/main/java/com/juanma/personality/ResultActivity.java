package com.juanma.personality;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;



public class ResultActivity extends AppCompatActivity {

    private RadarChart mRadarChart;
    private HorizontalBarChart mHorizontalBarChart1;
    private HorizontalBarChart mHorizontalBarChart2;
    private HorizontalBarChart mHorizontalBarChart3;
    private HorizontalBarChart mHorizontalBarChart4;
    private HorizontalBarChart mHorizontalBarChart5;

    private TextView mConscientiousnessTextView;
    private TextView mAgreeablenessTextView;
    private TextView mExtroversionTextView;
    private TextView mOpennessTextView;
    private TextView mNeuroticismTextview;

    private AdView mAdView;
    private String bannerAdUnit3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ;  //Second parameter is Id for Adview

        //setAdView();


        mRadarChart = findViewById(R.id.RADAR_CHART);
        mHorizontalBarChart1 = findViewById(R.id.HORIZONTAL_BAR_CHART_1);
        mHorizontalBarChart2 = findViewById(R.id.HORIZONTAL_BAR_CHART_2);
        mHorizontalBarChart3 = findViewById(R.id.HORIZONTAL_BAR_CHART_3);
        mHorizontalBarChart4 = findViewById(R.id.HORIZONTAL_BAR_CHART_4);
        mHorizontalBarChart5 = findViewById(R.id.HORIZONTAL_BAR_CHART_5);


        setRadarChart(mRadarChart);
        setHorizontalChart(mHorizontalBarChart1, 0);
        setHorizontalChart(mHorizontalBarChart2, 1);
        setHorizontalChart(mHorizontalBarChart3, 2);
        setHorizontalChart(mHorizontalBarChart4,3);
        setHorizontalChart(mHorizontalBarChart5,4);
        setTexTViews();




    }

    private void setAdView(){

        bannerAdUnit3 = "your banner id";
        MobileAds.initialize(this, bannerAdUnit3);
        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

    }

    private class MyValueFormatter extends ValueFormatter {

        @Override
        public String getBarLabel(BarEntry barEntry) {

            int intEntry = (int) barEntry.getY();
            return (intEntry) + "%";

        }

        @Override
        public String getRadarLabel(RadarEntry radarEntry) {
            int intEntry = (int) radarEntry.getValue();
            return (intEntry) + "%";
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            int intEntry = (int) value;
            return Integer.toString(intEntry);
        }
    }

    private ArrayList<RadarEntry> mRadarEntries(){

        final Answers FinalAnswer = Answers.getInstance();
        ArrayList<RadarEntry> datavalues = new ArrayList<RadarEntry>();
        datavalues.add(new RadarEntry(FinalAnswer.getAnswers(0)));
        datavalues.add(new RadarEntry(FinalAnswer.getAnswers(1)));
        datavalues.add(new RadarEntry(FinalAnswer.getAnswers(2)));
        datavalues.add(new RadarEntry(FinalAnswer.getAnswers(3)));
        datavalues.add(new RadarEntry(FinalAnswer.getAnswers(4)));
        return datavalues;
    }

    private void setHorizontalChart(HorizontalBarChart mHorizontalBarChart, int trait){

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        Answers answers = Answers.getInstance();
        int entry = answers.getAnswers(trait);
        barEntries.add(new BarEntry(1, entry));

        //COLORS
        int color = getResources().getColor(R.color.colorPrimary);

        //

        //Bar DataSet
        BarDataSet barDataSet;
        barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setValueTextSize(14);
        barDataSet.setColor(color);   //Color of Bar
        barDataSet.setLabel(""); // Turning Unnecessary Label Off
        barDataSet.setFormSize(0); // Getting Rid of Red Square
        barDataSet.setValueFormatter(new MyValueFormatter());

        //BarData Intialization
        BarData barData = new BarData(barDataSet);
        mHorizontalBarChart.setData(barData);

        //AXIS
        XAxis xAxis;
        xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setAxisMaximum(2);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setTextColor(Color.WHITE); //SMALL TEXT COLOR to the RIGHT
        xAxis.setGridColor(Color.WHITE);

        YAxis yAxis;
        yAxis = mHorizontalBarChart.getAxisRight();
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);

        YAxis yAxis1;
        yAxis1 = mHorizontalBarChart.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(100);
        yAxis1.setGridColor(Color.BLACK); //Horizontal Grid Color


        mHorizontalBarChart.animateXY(6000,6000, Easing.EaseOutSine);
        //Description Settings
        Description description = new Description();;
        description.setEnabled(false);
        mHorizontalBarChart.setDescription(description);

        //DISABLING TOUCH INTERACTIONS!
        mHorizontalBarChart.setClickable(false);
        mHorizontalBarChart.setDragEnabled(false);
        mHorizontalBarChart.setPinchZoom(false);
        mHorizontalBarChart.setHighlightPerTapEnabled(false);
        mHorizontalBarChart.setHighlightFullBarEnabled(false);
        mHorizontalBarChart.setHighlightPerDragEnabled(false);
        mHorizontalBarChart.setAutoScaleMinMaxEnabled(false);
        mHorizontalBarChart.setDrawGridBackground(false);
        mHorizontalBarChart.setDuplicateParentStateEnabled(false);
        mHorizontalBarChart.setTouchEnabled(false);
    }

    private void setRadarChart(RadarChart mRadarChart){

        String[] labels = {"Extroversion","Agreeableness", "Conscientiousness", "Neuroticism", "Openness"};
        RadarDataSet radarDataSet = new RadarDataSet(mRadarEntries(),"Personality");


        //COLORS
        int color = getResources().getColor(R.color.colorPrimaryDark);

        //GETTING RID OF STUFF
        radarDataSet.setLabel(""); //UNNECESSARY LABEL
        radarDataSet.setColor(Color.CYAN);// Color of LINES
        radarDataSet.setLineWidth(20);  //Red width
        radarDataSet.setFormSize(0); // Getting rid of Square

        //Setting the insides of the dataset
        radarDataSet.setFillColor(color);
        radarDataSet.setDrawFilled(true);
        radarDataSet.setLineWidth(2);


        // Set the Width of the Line
        radarDataSet.setValueTextColor(Color.WHITE); //Color of Filling
        radarDataSet.setValueTextSize(15);


        //RADAR DATA
        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSet);
        radarData.setValueFormatter(new MyValueFormatter());

        //XAXIS
        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setAxisMaximum(10000);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setTextColor(Color.WHITE);


        //xAxis.setTextColor(Color.WHITE);

        //Turn off Description
        Description description = new Description();
        description.setText(" ");

        //YAXIS
        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setMaxWidth(100);
        yAxis.setMinWidth(0);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(80);
        yAxis.setValueFormatter(new MyValueFormatter());
        yAxis.setTextColor(Color.GRAY);
        yAxis.setTextSize(10);

        //ANIMATIONS
        mRadarChart.animateXY(1200,1100, Easing.EaseOutQuad);
        mRadarChart.setData(radarData);
        mRadarChart.setDescription(description);

        //WEB COLOR
        mRadarChart.setWebLineWidth(4);
        //mRadarChart.setWebColor(Color.WHITE);

        //WEB COLOR INNNER
        mRadarChart.setWebLineWidthInner(4);
        // mRadarChart.setWebColorInner(Color.WHITE);

        mRadarChart.setBackgroundColor(Color.BLACK);

    }

    private void setTexTViews(){
        mAgreeablenessTextView = findViewById(R.id.TEXT_VIEW_AGREEABLENESS_DESCRIPTION);
        mConscientiousnessTextView = findViewById(R.id.TEXT_VIEW_CONSCIENTIOUSNESS_DESCRIPTION);
        mExtroversionTextView = findViewById(R.id.TEXT_VIEW_EXTROVERSION_DESCRIPTION);
        mOpennessTextView = findViewById(R.id.TEXT_VIEW_OPENNESS_DESCRIPTION);
        mNeuroticismTextview = findViewById(R.id.TEXT_VIEW_NEUROTICISM_DESCRIPTION);


        String[] Extroversion = getResources().getStringArray(R.array.ARRAY_EXTROVERSION);
        String[] Agreeableness = getResources().getStringArray(R.array.ARRAY_AGREEABLENESS);
        String[] Conscientiousness = getResources().getStringArray(R.array.ARRAY_CONSCIENTIOUSNESS);
        String[] Neuroticism = getResources().getStringArray(R.array.ARRAY_NEUROTICISM);
        String[] Openness = getResources().getStringArray(R.array.ARRAY_OPENNESS);

        setDescription(mExtroversionTextView, 0,Extroversion);
        setDescription(mAgreeablenessTextView,1,Agreeableness);
        setDescription(mConscientiousnessTextView,2,Conscientiousness);
        setDescription(mNeuroticismTextview,3,Neuroticism);
        setDescription(mOpennessTextView,4,Openness);

    }

    private void setDescription(TextView textView, int trait, String[] traitarray){


        Answers answers = Answers.getInstance();
        int personality_trait = answers.getAnswers(trait);

        if(personality_trait <= 25){
            textView.setText(traitarray[0]);
        }
        else if (personality_trait == 50){
            textView.setText(traitarray[4]);
        }
        else if(personality_trait > 25 && answers.getAnswers(trait) < 50){
            textView.setText(traitarray[1]);
        }
        else if(personality_trait > 50 && answers.getAnswers(trait) <= 75){
            textView.setText(traitarray[2]);
        }
        else if(personality_trait > 75){
            textView.setText(traitarray[3]);
        }


    }





}

