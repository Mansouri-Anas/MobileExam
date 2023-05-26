package com.example.Mansouri_Anas_exam_IIBDCC_23;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

// Initialize variable

    EditText editText;
    EditText editCapital;
    EditText editNombreHabitants;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable

        editText=findViewById(R.id.edit_text);
        editCapital=findViewById(R.id.edit_capital);
        editNombreHabitants=findViewById(R.id.edit_nombre);
        btAdd=findViewById(R.id.bt_add);
        btReset=findViewById(R.id.bt_reset);
        recyclerView=findViewById(R.id.recycler_view);

        // initialize database
        database=RoomDB.getInstance(this);

        // store database value in data list

        dataList=database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter=new MainAdapter(MainActivity.this,dataList);

        // set adapter

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get string from edit text
                String sText=editText.getText().toString().trim();
                String sCapital=editCapital.getText().toString().trim();
                String sNbr=editNombreHabitants.getText().toString().trim();

                // check condition
                if(!sText.equals(""))
                 {
                    // when text is not empty
                    // initialize main data

                 MainData data=new MainData();

                 //Set text on main data
                    data.setText(sText);
                    data.setCapital(sCapital);
                    data.setNombrehabitants(sNbr);

                    //Insert text in database
                    database.mainDao().insert(data);
                    //Clear edit text
                     editText.setText("");
                     editCapital.setText("");
                     editNombreHabitants.setText("");

                    //Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Suppression")
                        .setMessage("Etes vous sure ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.mainDao().delete(dataList);
                                dataList.clear();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Non", null)
                        .show();

            }
        });
    }
}
