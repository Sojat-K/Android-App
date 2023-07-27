package com.example.networkcommunications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private PictureAdapter pictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView pictureList = findViewById(R.id.picture_list);
        pictureAdapter = new PictureAdapter();
        pictureList.setAdapter(pictureAdapter);
        pictureList.setLayoutManager(new LinearLayoutManager(this));

        Button downloadButton = findViewById(R.id.download_button);
        downloadButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Download Picture")
                    .setItems(new CharSequence[]{"Face", "Dog", "Cat"}, (dialog, which) -> {
                        String pictureUrl = "https://loremflickr.com/150/150/";
                        switch (which) {
                            case 0:
                                pictureUrl += "face";
                                break;
                            case 1:
                                pictureUrl += "dog";
                                break;
                            case 2:
                                pictureUrl += "cat";
                                break;
                        }
                        pictureAdapter.addPicture(pictureUrl);
                    });
            builder.create().show();
        });
    }
}