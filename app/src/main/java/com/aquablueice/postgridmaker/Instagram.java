package com.aquablueice.postgridmaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Instagram extends AppCompatActivity {
    public AdView mAdView, addview;
    Uri uri;
    Button btn21, btn22, btn23, btn31, btn32, btn33, btn34;
    ImageView imageView;
    String grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        btn21 = (Button) findViewById(R.id.btnTwoOne);
        btn22 = (Button) findViewById(R.id.btnTwoTwo);
        btn23 = (Button) findViewById(R.id.btnTwoThree);
        btn31 = (Button) findViewById(R.id.btnThreeOne);
        btn32 = (Button) findViewById(R.id.btnThreeTwo);
        btn33 = (Button) findViewById(R.id.btnThreeThree);
        btn34 = (Button) findViewById(R.id.btnThreeFour);
        imageView = findViewById(R.id.imageView);

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




        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(2,1)
                        .start();
                grid = "21";
            }
        });

        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(2,2)
                        .start();
                grid = "22";
            }
        });

        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(2,3)
                        .start();
                grid = "23";
            }
        });

        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(3,1)
                        .start();
                grid = "31";
            }
        });

        btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(3,2)
                        .start();
                grid = "32";
            }
        });

        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "33";
            }
        });

        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(3,4)
                        .start();
                grid = "34";
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();

        if (resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                startCutting(bitmap);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(this, "You select nothing...", Toast.LENGTH_SHORT).show();
        }

    }

    public  void startCutting(Bitmap bitmap){
        List<Bitmap> bitmapList = new ArrayList<Bitmap>();
        switch (grid) {
            case "21":
                bitmapList = twoOne(bitmap);
                break;
            case "22":
                bitmapList = twoTwo(bitmap);
                break;
            case "23":
                bitmapList = twoThree(bitmap);
                break;
            case "31":
                bitmapList = threeOne(bitmap);
                break;
            case "32":
                bitmapList = threeTwo(bitmap);
                break;
            case "33":
                bitmapList = threeThree(bitmap);
                break;
            case "34":
                bitmapList = threeFour(bitmap);
                break;
        }

        for (int i = 0; i < bitmapList.size();i++){
            String count = String.valueOf(i);
            saveImage(bitmapList.get(i),count);
        }
    }

    public List<Bitmap> twoOne(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(forth);
        return imgs;
    }
    public List<Bitmap> twoTwo(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap left = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(left);
        Bitmap right= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(right);
        return imgs;
    }

    public List<Bitmap> twoThree(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, 0, picture.getHeight()/3, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/3, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, 0, (picture.getHeight()/3)*2, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, picture.getWidth()/2, (picture.getHeight()/3)*2, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(sixth);
        return imgs;
    }

    public List<Bitmap> threeOne(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(third);
        return imgs;
    }

    public List<Bitmap> threeTwo(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        imgs.add(sixth);
        return imgs;
    }

    public List<Bitmap> threeThree(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(sixth);
        Bitmap seven = Bitmap.createBitmap(picture, 0, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(seven);
        Bitmap eight= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(eight);
        Bitmap nine= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(nine);
        return imgs;
    }

    public List<Bitmap> threeFour(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/4, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/4, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/4)*2, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(sixth);
        Bitmap seven = Bitmap.createBitmap(picture, 0, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(seven);
        Bitmap eight= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(eight);
        Bitmap nine= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(nine);
        Bitmap ten = Bitmap.createBitmap(picture, 0, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(ten);
        Bitmap eleven= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(eleven);
        Bitmap twelve= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        imgs.add(twelve);
        return imgs;
    }



    public void saveImage(Bitmap image, String nam){
        try {
            String root = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString();
            File myDir = new File(root + "/postGridMaker by AquaBlueIce");
            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
            myDir.mkdirs();
            String fname =  "pgm-" + nam + "-" + timeStamp + ".jpg";
            File file = new File(myDir, fname);

            FileOutputStream out = new FileOutputStream(file);
            OutputStream outputStream = getContentResolver().openOutputStream(Uri.fromFile(file));
            Bitmap bm = image;
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            out.flush();
            out.close();
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));

            Toast.makeText(this , "Saved to "+ root + "/postGridMaker by AquaBlueIce", Toast.LENGTH_SHORT).show();
        } catch( Exception e) {
            Log.d("onBtnSavePng", e.toString());
        }

    }
}