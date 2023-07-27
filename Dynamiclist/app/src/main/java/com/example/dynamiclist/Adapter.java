package com.example.dynamiclist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.text.CollationElementIterator;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    public List<String> item;

    public Adapter(List<String> item) {
        this.item = item;
    }

    public static void add(List<String> item) {
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String Item = item.get(position);
        holder.TextView.setText(Item);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(String Item) {

        item.add(Item);
        notifyItemInserted(item.size() - 1);
    }

    public void removeItem() {
        item.remove(item.size() - 1);
        notifyItemRemoved(item.size());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public CollationElementIterator TextView;

        public ItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text);
        }
    }
}
