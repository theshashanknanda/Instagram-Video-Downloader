package com.project.youtubedownloader;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.EditText;

import com.hcr2bot.instagramvideosdownloader.InstaVideo;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public EditText editText;
    public Button pasteButton;
    public Button downloadButton;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        pasteButton = findViewById(R.id.paste_button);
        downloadButton = findViewById(R.id.download_button);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET}, 1);
        String url;
        getSupportActionBar().setTitle("Instagram Downloader");

        pasteButton.setOnClickListener(v -> {

            // Pasting Code
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = clipboard.getPrimaryClip();
            ClipData.Item item = clip.getItemAt(0);
            String pasteData= item.getText().toString();

            editText.setText(pasteData);
        });

        downloadButton.setOnClickListener(v -> {
            String ediTextData = editText.getText().toString().trim();

            if(!ediTextData.isEmpty()){
                // Downlaod code
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("The download has started");
                dialog.setMessage("Please wait");
                dialog.show();

                InstaVideo.downloadVideo(this, ediTextData);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 5000);
            }
        });
    }
}
