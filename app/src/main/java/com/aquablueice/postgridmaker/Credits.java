package com.aquablueice.postgridmaker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Credits extends AppCompatActivity {

    ImageButton ibtnPaypal, ibtnPatreon;
    TextView txtCredits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        ibtnPatreon = findViewById(R.id.ibtnpatreon);
        ibtnPaypal = findViewById(R.id.ibtnpaypal);

        txtCredits = findViewById(R.id.txtCred);

        AdView mAdView = findViewById(R.id.adView);
        AdView addview = (findViewById(R.id.adView2));
        AdRequest adRequest;
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        addview.loadAd(adRequest);


        Button btnCred = (Button) findViewById(R.id.btnCredits);
        btnCred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Credits.this, MainActivity.class));
            }
        });
        txtCredits.setMovementMethod(new ScrollingMovementMethod());


        ibtnPatreon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.patreon.com/aquablueice");
            }
        });
        ibtnPaypal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.paypal.me/aquablueice");
            }
        });
    }

    private void goLink(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
