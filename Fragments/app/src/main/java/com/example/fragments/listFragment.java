package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class listFragment extends Fragment {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    public listFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);

        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        return view;
    }

    public void addData(String name, String address) {
        String data = name + " - " + address;
        dataList.add(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("dataList", dataList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            dataList = savedInstanceState.getStringArrayList("dataList");
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
            ListView listView = getView().findViewById(R.id.listview);
            listView.setAdapter(adapter);
        }
    }
}
