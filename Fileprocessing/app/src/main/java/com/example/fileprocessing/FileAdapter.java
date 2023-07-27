package com.example.fileprocessing;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public  class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private final Context context;
    private final List<File> fileList;

    protected FileAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
        Log.d("FileAdapter", "File list size: " + fileList.size());
    }

    private FileAdapter(List<File> fileList) {
        this.fileList = fileList;
        context = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alertdialog , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File file = fileList.get(position);
        holder.fileNameTextView.setText(file.getName());

        holder.itemView.setOnClickListener(view -> {
            // Display the contents of the file in an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("File contents");
            EditText editText = new EditText(context);
            editText.setText(readFile(file));
            builder.setView(editText);
            builder.setPositiveButton("Write", (dialog, which) -> {
               
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    writeFile(file, content);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        });
    }



    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView fileNameTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.textView );
        }
    }
    private String readFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            inputStream.close();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    private void writeFile(File file, String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            Toast.makeText(context, "File saved: " + file.getName(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

