package com.example.a34011_73_06.learningenglish;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    Button back,upd_el,del_btn;
    EditText nameenglish,namefrench,exampleenglish,examplefrench;
    long id;

    /** Modification ou suppression d'un élément existant */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        back = (Button) findViewById(R.id.back2);
        upd_el = (Button) findViewById(R.id.upd_element);
        del_btn = (Button) findViewById(R.id.del_btn);
        nameenglish= (EditText) findViewById(R.id.nameenglish);
        namefrench= (EditText) findViewById(R.id.namefrench);
        exampleenglish= (EditText) findViewById(R.id.exampleenglish);
        examplefrench= (EditText) findViewById(R.id.examplefrench);

        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        nameenglish.setText(i.getStringExtra("nameenglish"));
        namefrench.setText(i.getStringExtra("namefrench"));
        exampleenglish.setText(i.getStringExtra("exampleenglish"));
        examplefrench.setText(i.getStringExtra("examplefrench"));

        upd_el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameenglish.getText().toString().length() > 0 && namefrench.getText().toString().length() > 0) {

                    ContactWord c = new ContactWord(getBaseContext());
                    c.open();
                    c.updateContact(id, nameenglish.getText().toString(),namefrench.getText().toString(), exampleenglish.getText().toString(), examplefrench.getText().toString());
                    Toast.makeText(getBaseContext(), "Mot Actualisé !!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getBaseContext(), "Erreur!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //  mise en ecoute du bouton 'retour'

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);

                builder.setTitle(" - Confirmer - ");
                builder.setMessage("Etes-vous sur de vouloir effacer ce mot  ?");

                builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ContactWord c = new ContactWord(getBaseContext());
                        c.open();
                        c.deleteContact(id);
                        finish();
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Mot effacé !!", Toast.LENGTH_LONG).show();

                    }

                });

                builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
