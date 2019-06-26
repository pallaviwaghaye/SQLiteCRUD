package com.practice.sqlitecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.sqlitecrud.SQLiteDB.DatabaseManager;
import com.practice.sqlitecrud.model.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewBack;
    private EditText edittextPname;
    private EditText edittextPaddress;
    private EditText edittextMtimedate;
    private EditText edittextPquali;
    private Button buttonSave;
    private ImageView imageViewShowList;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = (Button)findViewById(R.id.buttonSave);
        edittextPname = (EditText)findViewById(R.id.edittextPname);
        edittextPaddress = (EditText)findViewById(R.id.edittextPaddress);
        edittextMtimedate = (EditText)findViewById(R.id.edittextMtimedate);
        edittextPquali = (EditText)findViewById(R.id.edittextPquali);

        imageViewShowList = (ImageView)findViewById(R.id.imageViewShowList);
        imageViewShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        /*imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent1);
                finish();
            }
        });*/

        //to get previous text from database
        DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
        person = (Person) getIntent().getSerializableExtra("PersonObj");
        if (person != null) {
            edittextPname.setText(person.getPname());
            edittextPaddress.setText(person.getPaddress());
            edittextPquali.setText(person.getPqualification());
            edittextMtimedate.setText(person.getTimedate());



        }

        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSave :
                if (edittextPname.getText().toString().length() > 0) {
                    if (edittextPaddress.getText().toString().length() > 0) {
                        if (edittextPquali.getText().toString().length() > 0) {
                            if(person != null)
                            {
                                updateToDB();
                            }else {
                                insertToDB();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Please enter qualification", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Please enter address",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this,"Please enter name",Toast.LENGTH_LONG).show();
                }


                break;
        }
    }


    private void insertToDB() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy, hh:mm a");
        String formattedDate = df.format(c);
        person = new Person();
        person.setPname(edittextPname.getText().toString());
        person.setPaddress(edittextPaddress.getText().toString());
        person.setPqualification(edittextPquali.getText().toString());
        person.setTimedate(formattedDate);
        DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
        databaseManager.insertCallEntryDetails(person);

        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
    }



    private void updateToDB() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy, hh:mm a");
        String formattedDate = df.format(c);

        person.setPname(edittextPname.getText().toString());
        person.setPaddress(edittextPaddress.getText().toString());
        person.setPqualification(edittextPquali.getText().toString());
       // int id = person.getId();
        person.setTimedate(formattedDate);
        DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
        databaseManager.updateEntry(person);

        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
