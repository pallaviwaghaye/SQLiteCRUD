package com.practice.sqlitecrud.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.practice.sqlitecrud.MainActivity;
import com.practice.sqlitecrud.R;
import com.practice.sqlitecrud.SQLiteDB.DatabaseManager;
import com.practice.sqlitecrud.model.Person;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    /*Activity context;
    int size;*/
    private final DatabaseManager databaseManager;
    Activity context;
    List<Person> list = new ArrayList<>();
    RecyclerView recyclerView;
    TextView textView;

    public ListAdapter(Activity context, List<Person> list, RecyclerView recyclerView, TextView textView) {
        super();
        this.context = context;
        this.list = list;
        this.recyclerView = recyclerView;
        this.textView = textView;
        databaseManager = new DatabaseManager(context);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder viewHolder, final int position) {
        final Person person = list.get(position);
        viewHolder.textViewPname.setText(person.getPname());
        viewHolder.textViewPaddress.setText(person.getPaddress());
        viewHolder.textViewPquali.setText(person.getPqualification());
        viewHolder.textViewTimeDate.setText(person.getTimedate());

        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                deleteAndUpdate(person.getId(),position);
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();


            }
        });



        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("PersonObj",person);
                context.startActivity(intent);

            }
        });




    }

    private void deleteAndUpdate(int gmrid, int position) {
        int id = databaseManager.deleteEntry(gmrid);
        if (id > 0) {
            list.remove(position);
            this.notifyDataSetChanged();

            if(list.size() == 0) {
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
            }
        }
    }





    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textViewPname;
        private TextView textViewPaddress;
        private TextView textViewPquali;
        private TextView textViewTimeDate;
        private ImageView imageViewDelete;
        private ImageView imageViewEdit;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textViewPname = (TextView) itemView.findViewById(R.id.textViewPname);
            textViewPaddress = (TextView) itemView.findViewById(R.id.textViewPaddress);
            textViewTimeDate = (TextView) itemView.findViewById(R.id.textViewTimeDate);
            textViewPquali = (TextView) itemView.findViewById(R.id.textViewPquali);
            imageViewDelete = (ImageView)itemView.findViewById(R.id.imageViewDelete);
            imageViewEdit = (ImageView)itemView.findViewById(R.id.imageViewEdit);

        }
    }

}
