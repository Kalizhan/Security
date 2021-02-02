package com.example.security;

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

public class GuestsAdapter extends FirebaseRecyclerAdapter<Model, GuestsAdapter.viewHolder> {

    public GuestsAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Model model) {
        holder.fname.setText(model.getFname());
        holder.time.setText(model.getDate());
        holder.reason.setText(model.getReason());
        holder.who_add.setText(model.getAdd());

        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference item = getRef(position);
                        item.removeValue();
            }
        });
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView fname, time, reason, who_add;
        Button btn_del;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            time = itemView.findViewById(R.id.time);
            reason = itemView.findViewById(R.id.reason);
            who_add = itemView.findViewById(R.id.who_add);
            btn_del = itemView.findViewById(R.id.btn_del);
        }
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_tasks, parent, false);
        return new viewHolder(view);
    }
}
