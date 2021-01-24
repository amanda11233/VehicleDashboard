package com.example.daggerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.daggerapplication.models.User;
import com.example.daggerapplication.ui.auth.AuthActivity;
import com.example.daggerapplication.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();

    }



    private void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                switch(userAuthResource.status){
                    case LOADING:


                        break;
                    case AUTHENTICATED:

                        Toast.makeText(getApplicationContext(),
                                "Login Success" + userAuthResource.data.getEmail(),
                                Toast.LENGTH_LONG)
                                .show();
                        break;
                    case ERROR:


                        break;
                    case NOT_AUTHENTICATED:
                            navLoginScreen();
                        break;
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
