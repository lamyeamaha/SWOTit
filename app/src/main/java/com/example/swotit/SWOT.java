package com.example.swotit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SWOT extends AppCompatActivity {

    private EditText edtTxtStr, edtTxtWeakness, edtTxtOpp, edtTxtThreats;
    private String D2,D1;
    private String main_reason;
    private Button btnYes, btnNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swot_analysis_layout);

        System.out.println("entered swot's on Create method");

        Intent i = getIntent();
        if(i.hasExtra("keyD1"))
        {
            D1 = i.getStringExtra("keyD1");
            System.out.println("SWOT class got keyD1");
        }

        System.out.println("after getting keyD1");

        edtTxtStr = findViewById(R.id.edtTxtStrengths);
        edtTxtWeakness = findViewById(R.id.edtTxtWeakness);
        edtTxtOpp = findViewById(R.id.edtTxtOpportunities);
        edtTxtThreats = findViewById(R.id.edtTxtThreats);



        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("yes on swot clicked");
                String Strengths = edtTxtStr.getText().toString();
                String Weakness = edtTxtWeakness.getText().toString();
                String Opportunities = edtTxtOpp.getText().toString();
                String Threats = edtTxtThreats.getText().toString();

                D2= "Yes";
                String message = "I have decided to do it mainly because: ";

                DecisionModel d = new DecisionModel(Strengths, Weakness, Opportunities, Threats, D2, D1, null);

                //decisionLists.add(new DecisionModel());

                System.out.println("going to showdialogandsave fucntion");
                showDialogAndSave(message, d);

                //finish();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                D2= "No";
                String message = "I have decided to not do it mainly because: ";
                String Strengths = edtTxtStr.getText().toString();
                String Weakness = edtTxtWeakness.getText().toString();
                String Opportunities = edtTxtOpp.getText().toString();
                String Threats = edtTxtThreats.getText().toString();

                DecisionModel d = new DecisionModel(Strengths, Weakness, Opportunities, Threats, D2, D1, null);


                showDialogAndSave(message, d);
                //finish();
            }
        });

    }

    public void showDialogAndSave(String message, DecisionModel d)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);


        final EditText edittext = new EditText(SWOT.this);
        builder.setMessage(message);
        builder.setView(edittext);

        builder.setTitle("Confirm");
        builder.setCancelable(false);
        System.out.println("inside showdialogandsave function");
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        main_reason = edittext.getText().toString();
                        if(main_reason.isEmpty())
                        {
                            Toast.makeText(SWOT.this, "You did not enter any reason", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try {
                                System.out.println("here1");
                                DBHelper db = new DBHelper(SWOT.this);
                                boolean result  = db.insertData(D1, d.getStrengths(), d.getWeakness(), d.getOpportunities(), d.getThreats(), D2, main_reason);
                                db.close();
                                Toast.makeText(SWOT.this, "Decision Successfully Saved", Toast.LENGTH_LONG).show();
                                finish();

                            } catch(Exception ex)
                            {
                                System.out.println("here2");
                                ex.printStackTrace();
                            }
                        }



                        dialog.dismiss();
                        System.out.println("it comes here");

                    }
                });

        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Toast.makeText(SWOT.this, "Decision Not Saved", Toast.LENGTH_LONG).show();

                dialog.cancel();
            }
        });


        builder.create().show();
    }


}
