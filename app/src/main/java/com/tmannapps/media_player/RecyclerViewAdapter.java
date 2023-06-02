package com.tmannapps.media_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<URLs> linksList;
    private Context context;
    private OnRowClickListener listener;

    public RecyclerViewAdapter(List<URLs> linksList, Context context, OnRowClickListener clickListener) {
        this.linksList = linksList;
        this.context = context;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.playlist_row, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textViewLink.setText(linksList.get(position).getVideoLink());

    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewLink;
        public OnRowClickListener onRowClickListener;

        public ViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);

            textViewLink = itemView.findViewById(R.id.textViewLink);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }
        public interface OnRowClickListener {
            void onItemClick (int position);
        }
}