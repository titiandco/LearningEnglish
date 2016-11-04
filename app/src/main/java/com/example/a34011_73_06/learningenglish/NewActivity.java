package com.example.a34011_73_06.learningenglish;

/**
 * Created by 34011-73-06 on 28/10/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



    public class NewActivity extends AppCompatActivity {

        Button back,add_el;
        EditText nameenglish,namefrench,exampleenglish,examplefrench;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new);
            back = (Button) findViewById(R.id.back2);
            add_el = (Button) findViewById(R.id.add_element);
            nameenglish= (EditText) findViewById(R.id.nameenglish);
            namefrench= (EditText) findViewById(R.id.namefrench);
            exampleenglish= (EditText) findViewById(R.id.exampleenglish);
            examplefrench= (EditText) findViewById(R.id.examplefrench);



            add_el.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(nameenglish.getText().toString().length()>0 && namefrench.getText().toString().length()>0){


                        ContactWord c = new ContactWord(getBaseContext());
                        c.open();
                        c.createContact(nameenglish.getText().toString(), namefrench.getText().toString(), exampleenglish.getText().toString(), examplefrench.getText().toString());
                        nameenglish.setText("");
                        namefrench.setText("");
                        exampleenglish.setText("");
                        examplefrench.setText("");

                        Toast.makeText(getBaseContext(), "Nouveau mot enregistré!!",Toast.LENGTH_LONG).show();


                    }else{
                        Toast.makeText(getBaseContext(), "Erreur!!",Toast.LENGTH_LONG).show();
                    }
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }



}








