package com.example.a34011_73_06.learningenglish;

/**
 * Created by 34011-73-06 on 28/10/2016.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ElementsActivity extends ListActivity {

    Button back;
    ContactWord data;
    List<ContactWord> values;
    Intent i;
    public static boolean angfr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);
        i = getIntent();

        if (i.getAction().equals("angfr")){
            angfr = true;
        }
        else{
            angfr = false;
        }

        back = (Button) findViewById(R.id.back);
        data = new ContactWord(this);
        data.open();

        values = data.getAll();
        ArrayAdapter<ContactWord> adapter = new ArrayAdapter<ContactWord>(this, android.R.layout.simple_expandable_list_item_1, values);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(ElementsActivity.this,EditActivity.class);
                i.putExtra("id",values.get(position).id);
                i.putExtra("nameenglish",values.get(position).nameenglish);
                i.putExtra("namefrench",values.get(position).namefrench);
                i.putExtra("exampleenglish",values.get(position).exampleenglish);
                i.putExtra("examplefrench",values.get(position).examplefrench);

                startActivity(i);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

        values = data.getAll();
        ArrayAdapter<ContactWord> adapter = new ArrayAdapter<ContactWord>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
    }
}
