package com.aquablueice.postgridmaker;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.ContextWrapper;
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

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn2box, btn2V, btn2H, btn3B, btn1V2B, btn1H2B, btn4B,btn1V3B, btn1H3B, btn5B, btn2H3B, btn2B3H;
    String grid;
    Uri uri;
    public AdView mAdView, addview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btn2box = findViewById(R.id.btntwobox);
        btn2V = findViewById(R.id.btnTwoVertical);
        btn2H = findViewById(R.id.btnTwoHorizontal);
//        btn3B = findViewById(R.id.btnThreeBox);
        btn1V2B = findViewById(R.id.btnOneVerticalTwoBox);
        btn1H2B = findViewById(R.id.btnOneHorizontalTwoBox);
        btn4B = findViewById(R.id.btnFourBox);
        btn1V3B = findViewById(R.id.btnOneVThreeB);
        btn1H3B = findViewById(R.id.btnOneHThreeB);
        btn5B = findViewById(R.id.btnFiveB);
        btn2H3B = findViewById(R.id.btnTwoHThreeB);
        btn2B3H = findViewById(R.id.btnTwoBThreeH);

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


        btn2box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(2,1)
                        .start();

                grid = "2B";
            }
        });

        btn2V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(2,2)
                        .start();
                grid = "2V";
            }
        });

        btn2H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "2H";
            }
        });
//        btn3B.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImagePicker.with(MainActivity.this)
//                         .galleryOnly()
//                         .crop(3,1)
//                         .start();
//                grid = "3B";
//            }
//        });
        btn1V2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "1V2B";
            }
        });

        btn1H2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "1H2B";
            }
        });

        btn4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "4B";
            }
        });

        btn1V3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "1V3B";
            }
        });

        btn1H3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "1H3B";
            }
        });

        btn5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(6,5)
                        .start();
                grid = "5B";
            }
        });

        btn2H3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "2H3B";
            }
        });

        btn2B3H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(MainActivity.this)
                        .galleryOnly()
                        .crop(1,1)
                        .start();
                grid = "2B3H";
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        if (resultCode == 1){
            try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//            imageView.setImageURI(uri);
//            cropPic(bitmap);
//            splitBitmap(bitmap);

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                startCutting(bitmap);

//            tryToSaveImage(bitmap);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(this, "You pick nothing!", Toast.LENGTH_SHORT).show();
        }


    }

    public  void startCutting(Bitmap bitmap){
//        Bitmap[] bitmapList = new Bitmap[2];


        List<Bitmap> bitmapList = new ArrayList<Bitmap>();

        switch (grid) {
            case "2B":
                bitmapList = twoBox(bitmap);
                break;
            case "2V":
                bitmapList = twoVertical(bitmap);
                break;
            case "2H":
                bitmapList = twoHorizontal(bitmap);
                break;
            case "3B":
                bitmapList = threeBox(bitmap);
                break;
            case "1V2B":
                bitmapList = threeOneVerticalTwoBox(bitmap);
                break;
            case "1H2B":
                bitmapList = threeOneHorizontalTwoBox(bitmap);
                break;
            case "4B":
                bitmapList = fourBox(bitmap);
                break;
            case "1V3B":
                bitmapList = fourOneVerticalThreeBox(bitmap);
                break;
            case "1H3B":
                bitmapList = fourOneHorizontalThreeBox(bitmap);
                break;
            case "5B":
                bitmapList = fiveBox(bitmap);
                break;
            case "2H3B":
                bitmapList = fiveTwoHorizontalThreeBox(bitmap);
                break;
            case "2B3H":
                bitmapList = fiveTwoBoxThreeHorizontal(bitmap);
                break;

        }
//        bitmapList = twoBox(bitmap);

        for (int i = 0; i < bitmapList.size();i++){
            String count = String.valueOf(i);
            saveImage(bitmapList.get(i),count);

        }
//        newSaveImage(bitmapList.get(1));
//        storeImage(bitmapList.get(1));
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


//    public Bitmap[] splitBitmap(Bitmap picture) {
//        Bitmap[] imgs = new Bitmap[2];
//        imgs[0] = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight());
//        imgs[1] = Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth(), picture.getHeight());
//        return imgs;
//    }
    public List<Bitmap> twoBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap left = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(left);
        Bitmap right= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(right);
        return imgs;
    }

    public List<Bitmap> twoVertical(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap left = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(left);
        Bitmap right= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(right);
        return imgs;
    }

    public List<Bitmap> twoHorizontal(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap left = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight()/2);
        imgs.add(left);
        Bitmap right= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth(), picture.getHeight()/2);
        imgs.add(right);
        return imgs;
    }

    public List<Bitmap> threeBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight());
        imgs.add(third);
        return imgs;
    }

    public List<Bitmap> threeOneVerticalTwoBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight());
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(third);
        return imgs;
    }

    public List<Bitmap> threeOneHorizontalTwoBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight()/2);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(third);
        return imgs;
    }

    public List<Bitmap> fourBox(Bitmap picture) {
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

    public List<Bitmap> fourOneVerticalThreeBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, (picture.getWidth()/3)*2, picture.getHeight());
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(forth);
        return imgs;
    }

//    public List<Bitmap> fourOneHorizontalThreeBox(Bitmap picture) {
//        List<Bitmap> imgs = new ArrayList<Bitmap>();
//        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(),picture.getHeight()/(picture.getHeight()/picture.getWidth()));
//        imgs.add(first);
//        Bitmap second= Bitmap.createBitmap(picture, 0,picture.getHeight()/(picture.getHeight()/picture.getWidth()), picture.getWidth()/3,picture.getHeight()/(picture.getHeight()/(picture.getWidth()/3)));
//        imgs.add(second);
//        Bitmap third= Bitmap.createBitmap(picture, picture.getWidth()/3,picture.getHeight()/(picture.getHeight()/picture.getWidth()),picture.getWidth()/3, picture.getHeight()/(picture.getHeight()/(picture.getWidth()/3)));
//        imgs.add(third);
//        Bitmap forth= Bitmap.createBitmap(picture, (picture.getWidth()/3)/2,picture.getHeight()/(picture.getHeight()/picture.getWidth()),picture.getWidth()/3, picture.getHeight()/(picture.getHeight()/(picture.getWidth()/3)));
//        imgs.add(forth);
//        return imgs;
//    }

    public List<Bitmap> fourOneHorizontalThreeBox(Bitmap picture) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(picture, 900, 900, false);
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(scaledBitmap, 0, 0, 900,600);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(scaledBitmap, 0,600, 300,300);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(scaledBitmap, 300,600,300, 300);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(scaledBitmap, 600,600,300, 300);
        imgs.add(forth);
        return imgs;
    }


    public List<Bitmap> fiveBox(Bitmap picture) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(picture, 900, 750, false);
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(scaledBitmap, 0, 0, 450,450);
        imgs.add(first);
        Bitmap second = Bitmap.createBitmap(scaledBitmap, 450, 0, 450,450);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(scaledBitmap, 0,450, 300,300);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(scaledBitmap, 300,450,300, 300);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(scaledBitmap, 600,450,300, 300);
        imgs.add(fifth);
        return imgs;
    }

    public List<Bitmap> fiveTwoHorizontalThreeBox(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, (picture.getWidth()/3)*2, picture.getHeight()/2);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, (picture.getWidth()/3)*2, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        imgs.add(fifth);
        return imgs;
    }

    public List<Bitmap> fiveTwoBoxThreeHorizontal(Bitmap picture) {
        List<Bitmap> imgs = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(first);
        Bitmap second= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        imgs.add(second);
        Bitmap third= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/3, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/2, (picture.getHeight()/3)*2, picture.getWidth()/2, picture.getHeight()/3);
        imgs.add(fifth);
        return imgs;
    }

    public void saveImage(Bitmap image, String nam){
        try {
            String root = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString();
//            String rootanother = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Demo/";
            File myDir = new File(root + "/postGridMaker by AquaBlueIce");
            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
            myDir.mkdirs();
            String fname =  "pgm-" + nam + "-" + timeStamp + ".jpg";
            File file = new File(myDir, fname);

            FileOutputStream out = new FileOutputStream(file);
            OutputStream outputStream = getContentResolver().openOutputStream(Uri.fromFile(file));
            Bitmap bm = image;
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            out.flush();
            out.close();

            Toast.makeText(this , "Saved to "+ root + "/postGridMaker", Toast.LENGTH_SHORT).show();
        } catch( Exception e) {
            Log.d("onBtnSavePng", e.toString());
        }

    }


    private void tryToSaveImage(Bitmap image) {
        try {
            String root = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();
            String fname =  "1.jpg";
            File file = new File(myDir, fname);

            int quality = 100;
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void newSaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String fname = "Image-.jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
