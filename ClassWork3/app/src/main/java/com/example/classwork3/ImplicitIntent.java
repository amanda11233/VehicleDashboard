package com.example.classwork3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

public class ImplicitIntent extends AppCompatActivity implements View.OnClickListener{

EditText edit;
    Button camera, gallery, url, dialer;
    ImageView imgview;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        camera  = findViewById(R.id.camera);
        gallery  = findViewById(R.id.gallery);
        url = findViewById(R.id.url);
        dialer = findViewById(R.id.dialer);
        edit = findViewById(R.id.edittxt);

        imgview = findViewById(R.id.imgview);

        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        url.setOnClickListener(this);
        dialer.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

                break;

            case R.id.gallery :

                Intent galint = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galint, 2);

                break;

            case R.id.url:
                String url = "https://youtube.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                break;


            case R.id.dialer:

                Intent dialerint =  new Intent(Intent.ACTION_DIAL);
                startActivityForResult(dialerint, 3);

        }
    }

    private void saveCapImg(){
//        imgview.setImageBitmap(image);

        try{
            File mydir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
            mydir.mkdir();
            Random random = new Random();
            int a = random.nextInt(1000);
            File file = new File(mydir, "myimg"+a+".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(this, "Saved To" + mydir, Toast.LENGTH_SHORT).show();
        }catch(FileNotFoundException e){

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
             image = (Bitmap) data.getExtras().get("data");
            imgview.setImageBitmap(image);
            askPermission();
        }
        if (requestCode == 2){

        }

    }
    public void askPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        }else{
            saveCapImg();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length >0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveCapImg();
            }
            else{
                Toast.makeText(this, "No Permission", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
