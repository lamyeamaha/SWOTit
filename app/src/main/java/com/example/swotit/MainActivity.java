package com.example.swotit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button btnAddDecision;
    private String D1 = "";
    private RecyclerView mainRecylerView;
    private ArrayList<DecisionModel> decisionLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddDecision = findViewById(R.id.btnAddaDecision);
        mainRecylerView = findViewById(R.id.mainRecyclerView);
        decisionLists = new ArrayList<>();

        System.out.println("entered main activity on create");





        btnAddDecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("add decision clicked");
                AddDecision();

            }
        });
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        decisionLists.clear();

        System.out.println("entered main activity onresume");

        setUpDecisionModel();

        DecisionRecyclerViewAdapter adapter = new DecisionRecyclerViewAdapter(this, decisionLists);

        mainRecylerView.setAdapter(adapter);
        mainRecylerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("end of on resume");


    }

    public void setUpDecisionModel()
    {


        System.out.println("entered main activity setupdecisionmodel");
        DBHelper db = new DBHelper(this);
        Cursor rows = db.getAllData();

        //Cursor rows = db.execute("SELECT * FROM key_value_pairs");

        System.out.println("read all the data");

        while(rows.moveToNext()) {
            String D1 = rows.getString(1);     // returns the first column out of the 2 columns
            String D2 = rows.getString(6);
            String main_reason = rows.getString(7);



            DecisionModel d = new DecisionModel();
            d.setD1(D1);
            d.setD2(D2);
            d.setMainReason(main_reason);

            decisionLists.add(d);

        }
        db.close();

    }

    public void AddDecision()
    {

        System.out.println("add decision function entered");
        showDialog("Which decision you want to analyze?");

    }

    public void showDialog(String message){

        System.out.println("showdialog fucntion in entered");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        System.out.println("showdialog fucntion in entered2");


        final EditText edittext = new EditText(MainActivity.this);
        System.out.println("showdialog fucntion in entered3");


        builder.setMessage(message);
        builder.setTitle("Confirm");
        builder.setView(edittext);
        System.out.println("showdialog fucntion in entered4");
        builder.setCancelable(false)
                .setPositiveButton("SWOT it", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("positive button");


                        D1 = edittext.getText().toString();

//
//                        if(edittext.getText().toString() == null)
//                        {
//                            System.out.println("showdialog fucntion in entered6");
//                            Toast.makeText(MainActivity.this, "No decison entered!", Toast.LENGTH_SHORT);
//                            //dialog.cancel();
//                        }
//                        else {
//                            D1 = edittext.getText().toString();
//                        }

                        Intent i = new Intent(MainActivity.this, SWOT.class);
                        i.putExtra("keyD1", D1);
                        System.out.println("going to start an acitivity");
                        startActivity(i);

                        System.out.println(D1);


                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//Creating dialog box

        AlertDialog alert = builder.create();
        alert.show();

    }


}

