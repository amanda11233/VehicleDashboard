package com.example.classwork3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences msharedPrefence;
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    EditText email, password;
    Button loginbtn;
    CheckBox rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        rem = findViewById(R.id.remember);
        initSharePrefrence();

        email.setText(msharedPrefence.getString(EMAIL, "asd"));
        password.setText(msharedPrefence.getString(PASSWORD, "asd"));

        rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = msharedPrefence.edit();
                if(isChecked){

                    editor.putString(EMAIL, email.getText().toString());
                    editor.putString(PASSWORD, password.getText().toString());

                }else{
                    editor.clear();
                }
            }
        });



    }

    private void initSharePrefrence(){
        msharedPrefence = PreferenceManager.getDefaultSharedPreferences(this);
    }



}
