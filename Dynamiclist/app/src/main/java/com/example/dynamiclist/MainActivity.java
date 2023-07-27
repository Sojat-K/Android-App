package com.example.dynamiclist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    RecyclerView recyclerView;
    Button addbutton;
    Button removebutton;
    List<String> item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        addbutton = findViewById(R.id.addbutton);
        removebutton = findViewById(R.id.removebutton);

        Adapter adapter = new Adapter(item);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemText = editText.getText().toString();
                if (item.contains(itemText)) {
                    Toast.makeText(MainActivity.this, "Item already in list", Toast.LENGTH_SHORT).show();
                } else {
                    item.add(itemText);
                    adapter.notifyItemInserted(item.size() - 1);
                    editText.setText("");
                }
            }
        });


        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isEmpty()) {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                } else {
                    int lastPosition = item.size() - 1;
                    item.remove(lastPosition);
                    adapter.notifyItemRemoved(lastPosition);
                }
            }
        });

    }
}