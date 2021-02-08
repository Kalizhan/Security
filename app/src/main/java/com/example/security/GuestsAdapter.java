package com.example.security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class GuestsAdapter extends RecyclerView.Adapter<GuestsAdapter.viewHolder> {

    private List<Model> modelList;
    private MainActivity context;

//    public GuestsAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
//        super(options);
//    }

    public GuestsAdapter(List<Model> modelList, MainActivity context){
        this.modelList = modelList;
        this.context = context;
    }

//    @Override
//    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Model model) {
//        Model item = modelList.get(position);
//
//        holder.fname.setText(model.getFname());
//        holder.time.setText(model.getDate());
//        holder.reason.setText(model.getReason());
//        holder.who_add.setText(model.getAdd());
//
////        holder.btn_del.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                DatabaseReference item = getRef(position);
////                        item.removeValue();
////            }
////        });
//    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView fname, day, time, reason, who_add;
//        Button btn_del;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            reason = itemView.findViewById(R.id.reason);
            who_add = itemView.findViewById(R.id.who_add);
//            btn_del = itemView.findViewById(R.id.btn_del);
        }
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_tasks, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Model item = modelList.get(position);

        holder.fname.setText(item.getFname());
        holder.day.setText(item.getDay());
        holder.time.setText(item.getTime());
        holder.reason.setText(item.getReason());
        holder.who_add.setText(item.getAdd());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
