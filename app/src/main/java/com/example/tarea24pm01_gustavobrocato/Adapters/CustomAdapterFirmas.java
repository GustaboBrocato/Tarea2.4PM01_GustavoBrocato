package com.example.tarea24pm01_gustavobrocato.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea24pm01_gustavobrocato.Models.firmaItem;
import com.example.tarea24pm01_gustavobrocato.R;

import java.util.List;

public class CustomAdapterFirmas extends RecyclerView.Adapter<CustomAdapterFirmas.CustomViewHolder> {
    private List<firmaItem> dataList;
    private Context context;

    public CustomAdapterFirmas(Context context, List<firmaItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item_firmas, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        firmaItem data = dataList.get(position);
        holder.descripcion.setText(data.getItemDescription());
        holder.image.setImageBitmap(data.getImageBitmap());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion;
        ImageView image;

        public CustomViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.txtListItemDescription);
            image = itemView.findViewById(R.id.imageviewListItemImagenes);
        }
    }
}
