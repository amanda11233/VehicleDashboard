package com.example.classwork3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class WordActivity extends AppCompatActivity {


    ListView listView;
    TextView tvWord;
    Button btnOk;

    String[] words = {"apple", "orange","grapes","banana"};
    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        listView = findViewById(R.id.wordList);
        tvWord = findViewById(R.id.tvWord);
        btnOk = findViewById(R.id.btnOk);

        showWord(level);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr_word = tvWord.getText().toString();
                if (usr_word == words[level]){
                    level++;
                    showWord(level);
                }
                else{
                    Toast.makeText(WordActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String letter = parent.getItemAtPosition(position).toString();
                tvWord.append(letter);
            }
        });
    }

    public void showWord(int i){
        Character[] word = shuffleWord(words[i]);
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(WordActivity.this, R.layout.spinner_values, word);
        listView.setAdapter(adapter);
    }

    private Character[] shuffleWord(String word){
        ArrayList<Character> chars = new ArrayList<>(word.length());
        for (char c : word.toCharArray()){
            chars.add(c);
        }

        Collections.shuffle(chars);
        Character[] shuffeled = new Character[chars.size()];
        for (int i=0;i<shuffeled.length;i++){
            shuffeled[i] = chars.get(i);
        }
//        String shuffeledword = new String(shuffeled);
        return shuffeled;
    }
}
