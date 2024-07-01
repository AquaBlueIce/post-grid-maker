package com.aquablueice.postgridmaker;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    ImageButton btn12, btn13,btn21, btn22, btn23, btn31, btn32, btn33, btn34, btn35;
    Button btnSave;
    ImageView imageView;
    String grid;
    List<Bitmap> bitmapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);


        btn12 = (ImageButton) findViewById(R.id.btnOneTwo);
        btn13 = (ImageButton) findViewById(R.id.btnOneThree);
        btn21 = (ImageButton) findViewById(R.id.btnTwoOne);
        btn22 = (ImageButton) findViewById(R.id.btnTwoTwo);
        btn23 = (ImageButton) findViewById(R.id.btnTwoThree);
        btn31 = (ImageButton) findViewById(R.id.btnThreeOne);
        btn32 = (ImageButton) findViewById(R.id.btnThreeTwo);
        btn33 = (ImageButton) findViewById(R.id.btnThreeThree);
        btn34 = (ImageButton) findViewById(R.id.btnThreeFour);
        btn35 = (ImageButton) findViewById(R.id.btnThreeFive);
        btnSave = (Button) findViewById(R.id.btnSave);
        imageView = findViewById(R.id.imageView);

//        List<Bitmap> finalBitmapList;
//        finalBitmapList = new ArrayList<Bitmap>();

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


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmapList != null) {
                    for (int i = 0; i < bitmapList.size(); i++) {
                        String count = String.valueOf(i);
                        saveImage(bitmapList.get(i), count);
                    }
                } else {
                    Toast.makeText(Instagram.this, "No images to save", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(1,2)
                        .start();
                grid = "12";
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(1,3)
                        .start();
                grid = "13";
            }
        });
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

        btn35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Instagram.this)
                        .galleryOnly()
                        .crop(3,5)
                        .start();
                grid = "35";
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



    public void startCutting(Bitmap bitmap){
        bitmapList = new ArrayList<Bitmap>();
        switch (grid) {
            case "12":
                bitmapList = oneTwo(bitmap);
                break;
            case "13":
                bitmapList = oneThree(bitmap);
                break;
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
            case "35":
                bitmapList = threeFive(bitmap);
                break;
        }

//        for (int i = 0; i < bitmapList.size();i++){
//            String count = String.valueOf(i);
//            saveImage(bitmapList.get(i),count);
//        }
    }

    private void saveImage(Bitmap bitmap, String imageName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_" + imageName + ".jpg";

        OutputStream fos;
        try {
            fos = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), imageFileName));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            // Add the image to the gallery
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, imageFileName, null);

            Toast.makeText(this, "Image saved: " + imageFileName, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Error accessing file: " + e.getMessage());
        }
    }


    public List<Bitmap> oneTwo(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight()/2);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth(), picture.getHeight()/2);
        bitmaps.add(second);
        return bitmaps;
    }

    public List<Bitmap> oneThree(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight()/3);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, 0, picture.getHeight()/3, picture.getWidth(), picture.getHeight()/3);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, 0, (picture.getHeight()/3)*2, picture.getWidth(), picture.getHeight()/3);
        bitmaps.add(third);
        return bitmaps;
    }


    public List<Bitmap> twoOne(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight()/2);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/2);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        bitmaps.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/2, picture.getWidth()/2, picture.getHeight()/2);
        bitmaps.add(forth);
        return bitmaps;
    }
    public List<Bitmap> twoTwo(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap left = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight());
        bitmaps.add(left);
        Bitmap right= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight());
        bitmaps.add(right);
        return bitmaps;
    }

    public List<Bitmap> twoThree(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/2, 0, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, 0, picture.getHeight()/3, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(third);
        Bitmap forth= Bitmap.createBitmap(picture, picture.getWidth()/2, picture.getHeight()/3, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, 0, (picture.getHeight()/3)*2, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, picture.getWidth()/2, (picture.getHeight()/3)*2, picture.getWidth()/2, picture.getHeight()/3);
        bitmaps.add(sixth);
        return bitmaps;
    }

    public List<Bitmap> threeOne(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight());
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight());
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight());
        bitmaps.add(third);
        return bitmaps;
    }

    public List<Bitmap> threeTwo(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/2, picture.getWidth()/3, picture.getHeight()/2);
        bitmaps.add(sixth);
        return bitmaps;
    }

    public List<Bitmap> threeThree(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/3, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(sixth);
        Bitmap seven = Bitmap.createBitmap(picture, 0, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(seven);
        Bitmap eight= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(eight);
        Bitmap nine= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/3)*2, picture.getWidth()/3, picture.getHeight()/3);
        bitmaps.add(nine);
        return bitmaps;
    }

    public List<Bitmap> threeFour(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/4, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/4, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/4, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(sixth);
        Bitmap seven = Bitmap.createBitmap(picture, 0, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(seven);
        Bitmap eight= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(eight);
        Bitmap nine= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/4)*2, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(nine);
        Bitmap ten = Bitmap.createBitmap(picture, 0, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(ten);
        Bitmap eleven= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(eleven);
        Bitmap twelve= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/4)*3, picture.getWidth()/3, picture.getHeight()/4);
        bitmaps.add(twelve);
        return bitmaps;
    }

    public List<Bitmap> threeFive(Bitmap picture) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Bitmap first = Bitmap.createBitmap(picture, 0, 0, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(first);
        Bitmap second= Bitmap.createBitmap(picture, picture.getWidth()/3, 0, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(second);
        Bitmap third= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, 0, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(third);
        Bitmap forth = Bitmap.createBitmap(picture, 0, picture.getHeight()/5, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(forth);
        Bitmap fifth= Bitmap.createBitmap(picture, picture.getWidth()/3, picture.getHeight()/5, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(fifth);
        Bitmap sixth= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, picture.getHeight()/5, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(sixth);
        Bitmap seven = Bitmap.createBitmap(picture, 0, (picture.getHeight()/5)*2, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(seven);
        Bitmap eight= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/5)*2, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(eight);
        Bitmap nine= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/5)*2, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(nine);
        Bitmap ten = Bitmap.createBitmap(picture, 0, (picture.getHeight()/5)*3, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(ten);
        Bitmap eleven= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/5)*3, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(eleven);
        Bitmap twelve= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/5)*3, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(twelve);
        Bitmap thirteen = Bitmap.createBitmap(picture, 0, (picture.getHeight()/5)*4, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(thirteen);
        Bitmap fourteen= Bitmap.createBitmap(picture, picture.getWidth()/3, (picture.getHeight()/5)*4, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(fourteen);
        Bitmap fifteen= Bitmap.createBitmap(picture, (picture.getWidth()/3)*2, (picture.getHeight()/5)*4, picture.getWidth()/3, picture.getHeight()/5);
        bitmaps.add(fifteen);
        return bitmaps;
    }


//========================Nilipat sa taas
//    public void saveImage(Bitmap image, String nam){
//        try {
//            String root = Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES).toString();
//            File myDir = new File(root + "/postGridMaker by AquaBlueIce");
//            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
//            myDir.mkdirs();
//            String fname =  "pgm-" + nam + "-" + timeStamp + ".jpg";
//            File file = new File(myDir, fname);
//
//            FileOutputStream out = new FileOutputStream(file);
//            OutputStream outputStream = getContentResolver().openOutputStream(Uri.fromFile(file));
//            Bitmap bm = image;
//            bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            out.flush();
//            out.close();
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
//
//            Toast.makeText(this , "Saved to "+ root + "/postGridMaker by AquaBlueIce", Toast.LENGTH_SHORT).show();
//        } catch( Exception e) {
//            Log.d("onBtnSavePng", e.toString());
//        }
//
//    }
}