package com.example.classwork3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classwork3.adapters.StudentsAdapter;
import com.example.classwork3.database.DbHelper;
import com.example.classwork3.models.Students;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private EditText nametxt, emailtxt, phonetxt;
    private Button addbtn, showstu;
    private RecyclerView recyclerView;
    private static final String TAG = "DatabaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        nametxt = findViewById(R.id.name);
        emailtxt = findViewById(R.id.email);
        phonetxt = findViewById(R.id.phone);
        recyclerView = findViewById(R.id.showstudents);
        addbtn = findViewById(R.id.addStu);
        showstu = findViewById(R.id.showstu);
        final DbHelper helper = new DbHelper(this);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=  nametxt.getText().toString();
                String email = emailtxt.getText().toString();
                String phone = phonetxt.getText().toString();


                Students stu = new Students(name, email, phone);
                helper.addStudents(stu);
            }
        });

        showstu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              List<Students> stulist =  helper.getStudents();

               for(Students student : stulist){
                   StudentsAdapter adapter = new StudentsAdapter(getApplicationContext(),stulist);
                   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   recyclerView.setAdapter(adapter);


               }
            }
        });
    }
}
