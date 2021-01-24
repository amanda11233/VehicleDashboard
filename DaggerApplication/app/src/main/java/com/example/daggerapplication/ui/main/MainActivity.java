package com.example.daggerapplication.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggerapplication.BaseActivity;
import com.example.daggerapplication.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
