package com.example.diana.weightcontrol;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class JournalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private Context context;

    public JournalAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new JournalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {
        final JournalViewHolder journalViewHolder = (JournalViewHolder) holder;
        journalViewHolder.date.setText(notes.get(i).getDate());
        journalViewHolder.weightField.setText("" + notes.get(i).getWeight());
        journalViewHolder.weightChange.setText("" + notes.get(i).getChange());
        if (notes.get(i).getChange().charAt(0) == '+') {
            journalViewHolder.weightChange.setTextColor(Color.parseColor("#E53935"));
        } else {
            journalViewHolder.weightChange.setTextColor(Color.parseColor("#43A047"));
        }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class JournalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        public TextView date;
        @BindView(R.id.weightField)
        public TextView weightField;
        @BindView(R.id.weightChange)
        public TextView weightChange;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
