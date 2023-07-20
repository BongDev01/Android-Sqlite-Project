package com.example.sqlitetest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Model> modelList;
    DBHelper dbHelper;

    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position%2 ==0){
            holder.ll.setBackgroundColor(Color.CYAN);
        }

        dbHelper = new DBHelper(context);

        holder.name.setText(modelList.get(position).Name);
        holder.age.setText(modelList.get(position).Age);
        holder.address.setText(modelList.get(position).Address);
        holder.edit_btn.setOnClickListener(v -> {
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("isFromActivity2",true);
            intent.putExtra("Name",modelList.get(position).Name);
            context.startActivity(intent);
        });

        holder.dlt.setOnClickListener(v -> {
         dbHelper.deleteValue(modelList.get(position));
         notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,age,address;
        LinearLayout ll;
        ImageView edit_btn,dlt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nme);
            age = itemView.findViewById(R.id.ag);
            address = itemView.findViewById(R.id.add);
            ll = itemView.findViewById(R.id.main);
            edit_btn = itemView.findViewById(R.id.editbtn);
            dlt = itemView.findViewById(R.id.dltbtn);
        }
    }
}
