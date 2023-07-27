package com.example.networkcommunications;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private List<String> pictureUrls;
    private Context context;

    public PictureAdapter (List<String> pictureUrls, Context context) {
        this.pictureUrls = pictureUrls;
        this.context = context;
    }

    public PictureAdapter() {

    }


    public void addPicture(String pictureUrl) {
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        public ImageView pictureImage;
        public Button downloadButton;

        

        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureImage = itemView.findViewById(R.id.picture_image);
            downloadButton = itemView.findViewById(R.id.download_button);
        }
    }

    @Override
    public PictureViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_layout , parent, false);

        return new PictureViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(final PictureViewHolder  holder, int position) {
        final String pictureUrl = pictureUrls.get(position);

        holder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImageTask(holder).execute(pictureUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictureUrls.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private PictureViewHolder  holder;

        public DownloadImageTask(PictureViewHolder  holder) {
            this.holder = holder;
        }

        protected Bitmap doInBackground(String... urls) {
            String pictureUrl = urls[0];
            try {
                URL url = new URL(pictureUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap pictureBitmap = BitmapFactory.decodeStream(input);
                return pictureBitmap;
            } catch (Exception e) {
                Log.e("DownloadImageTask", "Error downloading image", e);
                return null;
            }
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                holder.pictureImage.setImageBitmap(result);
            }
        }
    }
}
