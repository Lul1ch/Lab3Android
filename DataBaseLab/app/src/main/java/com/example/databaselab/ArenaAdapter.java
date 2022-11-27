package com.example.databaselab;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArenaAdapter extends RecyclerView.Adapter<ArenaAdapter.ArenaViewHolder> {
    private Context mCtx;
    private List<CombatResult> taskList;
    private List<PlayerDao.PlInfo> playerList;

    public ArenaAdapter(Context mCtx, List<CombatResult> taskList, List<PlayerDao.PlInfo> dopInfo) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.playerList = dopInfo;
    }

    @Override
    public ArenaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.rv_arena, parent, false);
        return new ArenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArenaViewHolder holder, int position) {
        CombatResult t = taskList.get(position);
        holder.textViewName1.setText(t.getFNickName());
        holder.textViewName2.setText(t.getSNickName());
        int pos = position*2;

        holder.textViewClass1.setText(this.playerList.get(pos).getPlayerClass());
        holder.textViewLevel1.setText(String.valueOf(this.playerList.get(pos).getPlayerLevel()));

        holder.textViewClass2.setText(this.playerList.get(pos+1).getPlayerClass());
        holder.textViewLevel2.setText(String.valueOf(this.playerList.get(pos+1).getPlayerLevel()));

        holder.ResultCombat.setText(t.getResult());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class ArenaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName1, textViewClass1, textViewLevel1, textViewName2, textViewClass2, textViewLevel2, ResultCombat;

        public ArenaViewHolder(View itemView) {
            super(itemView);

            textViewName1 = itemView.findViewById(R.id.pl_nick1);
            textViewClass1 = itemView.findViewById(R.id.pl_class1);
            textViewLevel1 = itemView.findViewById(R.id.pl_lvl1);

            textViewName2 = itemView.findViewById(R.id.pl_nick2);
            textViewClass2 = itemView.findViewById(R.id.pl_class2);
            textViewLevel2 = itemView.findViewById(R.id.pl_lvl2);

            ResultCombat = itemView.findViewById(R.id.result);
        }
    }

}