package com.example.fileprocessing;

import static android.provider.Settings.System.DATE_FORMAT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_DIRECTORY ="MyFiles" ;

    Button button;
    RecyclerView recyclerview;
    FileAdapter adapter;
    List<File> fileList;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        fileList = new ArrayList<>();

        File[] files = getFilesDir().listFiles();
        fileList.addAll(Arrays.asList(files));
        adapter.notifyDataSetChanged();

        adapter = new FileAdapter( this, fileList);
        recyclerview.setAdapter(adapter);



        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss", Locale.getDefault())
                        .format(new Date());

                File file = new File(getFilesDir(), "File created at "  + timeStamp + ".txt");
                try {
                    if (file.createNewFile()) {
                        Toast.makeText(MainActivity.this, "File created: " + file.getName(),
                                Toast.LENGTH_SHORT).show();
                    } //else {
                       // Toast.makeText(MainActivity.this, "File already exists",
                          //      Toast.LENGTH_SHORT).show();
                   // }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                fileList.add(file);
                adapter.notifyItemInserted(fileList.size() - 1);
            }

        });


    }


}