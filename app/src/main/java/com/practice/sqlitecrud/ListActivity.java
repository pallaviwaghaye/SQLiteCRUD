package com.practice.sqlitecrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.practice.sqlitecrud.SQLiteDB.DatabaseManager;
import com.practice.sqlitecrud.adapter.ListAdapter;
import com.practice.sqlitecrud.model.Person;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    //private Button buttonSync;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManger;
    private TextView textViewNoData;

    private ImageView imageViewBack;
    private ProgressDialog progressDialogForAPI;
    DatabaseManager databaseManager;

    List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        databaseManager = new DatabaseManager(ListActivity.this);
        list = databaseManager.getAllEntries();

        //buttonSync = (Button)findViewById(R.id.buttonSync);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        recyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(ListActivity.this);
        recyclerView.setLayoutManager(mLayoutManger);

        if (list != null && list.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new ListAdapter(ListActivity.this, list, recyclerView, textViewNoData));
            textViewNoData.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            textViewNoData.setVisibility(View.VISIBLE);
        }


    }

}
