package com.example.databaselab;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private Context mCtx;
    private List<Player> taskList;

    public PlayerAdapter(Context mCtx, List<Player> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.rv_main, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player t = taskList.get(position);
        holder.textViewName.setText(t.getNickname());
        holder.textViewClass.setText(t.getClassName());
        holder.textViewLevel.setText(String.valueOf(t.getLevel()));

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewClass, textViewLevel;

        public PlayerViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.pl_nick);
            textViewClass = itemView.findViewById(R.id.pl_class);
            textViewLevel = itemView.findViewById(R.id.pl_lvl);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Player task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdatePlayerActivity.class);
            intent.putExtra("player", task);

            mCtx.startActivity(intent);
        }
    }
}