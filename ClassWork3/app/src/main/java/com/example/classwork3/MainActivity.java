package com.example.classwork3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button savebtn, loadbtn, saveex;

    HashMap<String, String> myhm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittxt);
        savebtn  = findViewById(R.id.save);
        loadbtn = findViewById(R.id.load);
        saveex  = findViewById(R.id.saveex);
        getPremission();

        saveex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(isExWritable()){
                 Toast.makeText(MainActivity.this, "true", Toast.LENGTH_LONG).show();
             }


            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();

                try{
                    FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);
                    fos.write(data.getBytes());
                    Toast.makeText(MainActivity.this, "Saved to" + getFilesDir(),Toast.LENGTH_LONG).show();
                    editText.setText(null);
                }catch(IOException e){
                    Log.d("Error", e.toString());
                }
            }
        });

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fis = openFileInput("myfile.txt");
                    InputStreamReader inr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(inr);
//                  StringBuilder sb  = new StringBuilder();
                    String data;
                    String alldata = "";
                    while(( data = br.readLine()) != null){
//                        alldata += data + "\n";
                        String[] wm = data.split("=");
                        myhm.put(wm[0], wm[1]);
                    }
                    editText.setText(alldata);
                }catch (IOException e){
                    Log.d("Error", e.toString());
                }

            }
        });

    }

    private void getPremission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        }else{
            saveExternal();
        }
    }

    private void saveExternal(){

        if(isExWritable()){
            String data = editText.getText().toString();
            try{
                File mydir = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MyApplic");
                mydir.mkdir();
                File myfile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MyApplic/myfile.txt");
                myfile.createNewFile();
                FileOutputStream fos = new FileOutputStream(myfile);
                fos.write(data.getBytes());
                editText.getText().clear();
                Toast.makeText(this, "Saved to" + mydir, Toast.LENGTH_LONG).show();
            }catch(Exception e){
                Log.d("Error", e.toString());
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length >0 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveExternal();
            }
            else{
                Toast.makeText(this, "No Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isExWritable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return true;
        }
        else{
            return false;
        }
    }
}
