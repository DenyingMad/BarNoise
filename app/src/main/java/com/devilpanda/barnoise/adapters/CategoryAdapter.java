package com.devilpanda.barnoise.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devilpanda.barnoise.R;
import com.devilpanda.barnoise.model.Subject;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Subject> subjects;
    private OnSubjectListener listener;

    public CategoryAdapter(List<Subject> subjects, OnSubjectListener listener){
        this.listener = listener;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(subjects.get(position).getTitle());
        holder.desc.setText(subjects.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView desc;
        private OnSubjectListener onSubjectListener;

        public ViewHolder(@NonNull View itemView, OnSubjectListener onSubjectListener) {
            super(itemView);
            title = itemView.findViewById(R.id.subject_title);
            desc = itemView.findViewById(R.id.subject_decription);
            this.onSubjectListener = onSubjectListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSubjectListener.onSubjectClick(getAdapterPosition());
        }
    }

    public interface OnSubjectListener{
        public void onSubjectClick(int position);
    }
}
