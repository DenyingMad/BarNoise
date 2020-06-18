package com.devilpanda.barnoise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.model.Category;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;
    private OnCategoryListener onCategoryListener;

    public HomeAdapter(Context context, List<Category> categories, OnCategoryListener onCategoryListener){
        this.categories = categories;
        this.context = context;
        this.onCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false);
        return new ViewHolder(view, onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnCategoryListener listener;
        private TextView textView;
        public ViewHolder(@NonNull View itemView, OnCategoryListener listener) {
            super(itemView);
            this.listener = listener;
            textView = itemView.findViewById(R.id.home_button_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onListener(getAdapterPosition());
        }
    }

    public interface OnCategoryListener{
        public void onListener(int position);
    }
}
