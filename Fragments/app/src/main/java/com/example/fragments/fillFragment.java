package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class fillFragment extends Fragment {

    private EditText nameEditText;
    private EditText addressEditText;
    private Button addButton;

    public fillFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill, container, false);

        nameEditText = view.findViewById(R.id.name);
        addressEditText = view.findViewById(R.id.address);
        addButton = view.findViewById(R.id.add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String address = addressEditText.getText().toString();

                if (!name.isEmpty() && !address.isEmpty()) {
                    addData(name, address);
                    clearFields();
                } else {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void addData(String name, String address) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            listFragment listFragment = (listFragment) activity.getSupportFragmentManager().findFragmentById(R.id.listview);
            if (listFragment != null) {
                listFragment.addData(name, address);
            }
        }
    }

    private void clearFields() {
        nameEditText.setText("");
        addressEditText.setText("");
    }
}
