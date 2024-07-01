package com.aquablueice.postgridmaker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {


    Button btnFB, btnIG, btnCredits;
    ImageButton ibtnDc, ibtnYoutube, ibtnX, ibtnInstagram, ibtnTwitch, ibtnTiktok;
    public AdView mAdView, addview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        addview = (findViewById(R.id.adView2));
        AdRequest adRequest;
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        addview.loadAd(adRequest);


        btnFB = (Button)findViewById(R.id.btnFacebook);
        btnIG = (Button)findViewById(R.id.btnInstagram);
        btnCredits = (Button)findViewById(R.id.btnCredits);
        ibtnDc = findViewById(R.id.ibtndiscord);
        ibtnYoutube = findViewById(R.id.ibtnyoutube);
        ibtnX = findViewById(R.id.ibtnx);
        ibtnInstagram = findViewById(R.id.ibtninstagram);
        ibtnTwitch = findViewById(R.id.ibtntwitch);
        ibtnTiktok = findViewById(R.id.ibtntiktok);




        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Facebook.class));
            }
        });

        btnIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Instagram.class));
            }
        });

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Credits.class);
                startActivity(intent);
            }
        });

        ibtnDc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://discord.gg/vNDyrXUNrH");
            }
        });

        ibtnYoutube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.youtube.com/@aquablueice");
            }
        });

        ibtnX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://x.com/aquablueiceph");
            }
        });

        ibtnInstagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.instagram.com/aquablueiceph/");
            }
        });

        ibtnTwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.twitch.tv/aquablueice");
            }
        });

        ibtnTiktok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goLink("https://www.tiktok.com/@aquablueice");
            }
        });
    }

    private void goLink(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void notif () {
        Toast.makeText(this, "Feature Not Implemented yet...",Toast.LENGTH_SHORT).show();
    }
}


