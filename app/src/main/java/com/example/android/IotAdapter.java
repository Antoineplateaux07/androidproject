package com.example.android;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class IotAdapter extends RecyclerView.Adapter<IotAdapter.ViewHolder>{

    private Context context;
    private List<Iot> list;

    public IotAdapter(Context context, List<Iot> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Iot iot = list.get(position);
        holder.textId.setText(String.valueOf(iot.getId()));
        holder.textDate.setText(String.valueOf(iot.getDate()));
        holder.textHygro.setText(iot.getHygro());
        holder.textTemp.setText(String.valueOf(iot.getTemp()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textId,textDate, textHygro ,textTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.main_id);
            textDate = itemView.findViewById(R.id.main_date);
            textHygro = itemView.findViewById(R.id.main_hygro);
            textTemp = itemView.findViewById(R.id.main_temp);
        }
    }

}
