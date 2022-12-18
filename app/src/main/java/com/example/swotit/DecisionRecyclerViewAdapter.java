package com.example.swotit;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DecisionRecyclerViewAdapter extends RecyclerView.Adapter<DecisionRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<DecisionModel> decisionLists;

    public DecisionRecyclerViewAdapter(Context context, ArrayList<DecisionModel> decisionLists) {
        this.context = context;
        this.decisionLists = decisionLists;
    }

    @NonNull
    @Override
    public DecisionRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout (give a loot to the rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.decision_row_layout, parent, false);
        return new DecisionRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DecisionRecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views in the row layout file based on the position o the recycler view
        holder.txtViewD1.setText(decisionLists.get(position).getD1());
        if(decisionLists.get(position).getD2().equals("Yes"))
        {
            holder.txtView1.setText("I have chosen to do it mainly because: ");
        }
        else {
            holder.txtView1.setText("I have chosen not to do it mainly because: ");
        }

        holder.txtMainReason.setText(decisionLists.get(position).getMainReason());



    }

    @Override
    public int getItemCount() {
        // number of items you want to get displayed
        return decisionLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from the row layout file

        TextView txtViewD1, txtView1, txtMainReason;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView1 = itemView.findViewById(R.id.txtView1);
            txtViewD1 = itemView.findViewById(R.id.txtViewD1);
            txtMainReason = itemView.findViewById(R.id.txtMainReason);

        }
    }
}
