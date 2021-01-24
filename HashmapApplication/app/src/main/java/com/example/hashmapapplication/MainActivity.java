package com.example.hashmapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, String> dictonary;
    String countries[] = {"Nepal", "Kathmandu", "India", "Dehli", "China", "Beijing", "America", "Washington D.C"};
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listview= findViewById(R.id.listview);

        dictonary = new HashMap<>();
        for(int i=0; i<countries.length; i+=2 ){
            dictonary.put(countries[i],countries[i+1]);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList(dictonary.keySet()));

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country = parent.getItemAtPosition(position).toString();
                String capital = dictonary.get(country);
                Toast.makeText(getApplicationContext(), capital, Toast.LENGTH_LONG).show();
            }
        });

    }


}
