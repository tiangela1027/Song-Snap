package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.BuildConfig;
import com.androidtutorialshub.loginregister.MainActivity;
import com.androidtutorialshub.loginregister.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.PendingIntent.getActivity;

public class ShareActivity extends AppCompatActivity {
    private File file;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create new Intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        sendWhatsAppAudio();
//        Intent intent = getIntent();
//        String lName = intent.getStringExtra("lname");
//       // intent.setType("audio/mp3");
//        file  = new File(lName);
//        try {
//              uri = FileProvider.getUriForFile(
//                    getApplicationContext(),
//                    "com.example.myapp.fileprovider",
//                    file);
//        } catch (IllegalArgumentException e) {
//            Log.e("File Selector",
//                    "The selected file can't be shared: " + file.toString());
//        }
//            intent.putExtra(Intent.EXTRA_STREAM, uri);
//            if (uri != null) {
//                // Grant temporary read permission to the content URI
//               intent.addFlags(
//                        Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            }
//
//            if (uri != null) {
//          intent.setDataAndType(
//                    uri,
//                    getContentResolver().getType(uri));
//            // Set the result
//            ShareActivity.this.setResult(Activity.RESULT_OK,
//                    intent);
//        } else {
//            intent.setDataAndType(null, "");
//            ShareActivity.this.setResult(RESULT_CANCELED,
//                    intent);
//        }
//
//    }
    }
        private void sendWhatsAppAudio () {
            try {
                //Copy file to external ExternalStorage.
                String mediaPath = copyFiletoExternalStorage(R.raw.jorgesys_sound, "jorgesys_sound.mp3");

                Intent shareMedia = new Intent(Intent.ACTION_SEND);
                //set WhatsApp application.
                shareMedia.setPackage("com.whatsapp");
                shareMedia.setType("audio/*");
                //set path of media file in ExternalStorage.
                shareMedia.putExtra(Intent.EXTRA_STREAM, Uri.parse(mediaPath));
                startActivity(Intent.createChooser(shareMedia, "Compartiendo archivo."));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Whatsapp no se encuentra instalado", Toast.LENGTH_LONG).show();
            }
        }

        private String copyFiletoExternalStorage ( int resourceId, String resourceName){
            String pathSDCard = Environment.getExternalStorageDirectory() + "/Android/data/" + resourceName;
            try {
                InputStream in = getResources().openRawResource(resourceId);
                FileOutputStream out = null;
                out = new FileOutputStream(pathSDCard);
                byte[] buff = new byte[1024];
                int read = 0;
                try {
                    while ((read = in.read(buff)) > 0) {
                        out.write(buff, 0, read);
                    }
                } finally {
                    in.close();
                    out.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pathSDCard;
        }


    }


