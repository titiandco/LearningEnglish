package com.example.a34011_73_06.learningenglish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button allEngFr,allFrEng,newWord;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allEngFr = (Button)findViewById(R.id.elements);
        newWord = (Button)findViewById(R.id.new_element);
        allFrEng = (Button)findViewById(R.id.elements2);

        /** Ecouteur pour envoi sur la page d'affichage par ordre alphabètique des mots anglais */
        allEngFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ElementsActivity.class);
                i.setAction("angfr");
                startActivity(i);
            }
        });

        /** Ecouteur pour envoi sur la page de saisie d'une nouvelle traduction */
        newWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);
            }
        });

        /** Ecouteur pour envoi sur la page d'affichage par ordre alphabètique des mots français */
        allFrEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ElementsActivity.class);
                i.setAction("frang");
                startActivity(i);
            }
        });


    }
}
