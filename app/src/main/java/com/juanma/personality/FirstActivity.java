package com.juanma.personality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class FirstActivity extends AppCompatActivity {

    private AdView mAdView;
    private String bannerAdUnit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //setAdView();
        Button button = findViewById(R.id.Button_agree);
        setButton(button);


    }

    public void setAdView(){

        bannerAdUnit1 = "your banner id";
        MobileAds.initialize(this, bannerAdUnit1);  //Second parameter is Id for Adview
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

    }


    public void setButton(Button button){

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });




    }



}
