package com.example.daggerapplication.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.daggerapplication.R;
import com.example.daggerapplication.models.User;
import com.example.daggerapplication.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;
    private EditText userid;
    private Button loginbtn;
    private ProgressBar progressBar;


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userid = findViewById(R.id.user_id_input);
        loginbtn = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);


        loginbtn.setOnClickListener(this);

    viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
       setLogo();
        subscribeObservers();
    }


    private void subscribeObservers() {
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.status){
                        case LOADING:
                            showProgressBar(true);

                            break;
                        case AUTHENTICATED:
                            showProgressBar(false);
                            Toast.makeText(getApplicationContext(),
                                    "Login Success" + userAuthResource.data.getEmail(),
                                    Toast.LENGTH_LONG)
                                    .show();
                            break;
                        case ERROR:
                            Toast.makeText(getApplicationContext(),
                                    "No User Found",
                                    Toast.LENGTH_LONG)
                                    .show();
                            showProgressBar(false);
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;
                    }
                }
            }
        });

    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else if(!isVisible){
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo(){
        requestManager.load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_button){
            attemptLogin();

        }
    }

    private void attemptLogin(){
        if(TextUtils.isEmpty(userid.getText().toString())){
            return;
        }
        Toast.makeText(getApplicationContext(), "asd" , Toast.LENGTH_LONG).show();
        viewModel.authenticateWithid(Integer.parseInt(userid.getText().toString()));

    }
}
