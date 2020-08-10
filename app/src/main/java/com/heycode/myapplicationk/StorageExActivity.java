package com.heycode.myapplicationk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.UUID;

public class StorageExActivity extends AppCompatActivity {

    ImageView upload_image;
    TextView upload_text;
    Button upload_button;
    FirebaseStorage mStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_ex);

        upload_image = findViewById(R.id.upload_img);
        upload_text = findViewById(R.id.upload_txt);
        upload_button = findViewById(R.id.upload_btn);
        mStorage = FirebaseStorage.getInstance();

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_image.setDrawingCacheEnabled(true);
                upload_image.buildDrawingCache();
                Bitmap bitmap = upload_image.getDrawingCache();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                upload_image.setDrawingCacheEnabled(false);
                byte[] bytes = byteArrayOutputStream.toByteArray();

                //saving in mentioned dir
                String path = "firstFolder/"+ UUID.randomUUID()+".png";
                StorageReference reference = mStorage.getReference(path);

                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setCustomMetadata("text",upload_text.getText().toString()).build();
                UploadTask uploadTask = reference.putBytes(bytes,metadata);//image & text
                upload_button.setEnabled(false);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        upload_button.setEnabled(true);
                        Task<Uri> url = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        upload_text.setText(url.toString());


                        Toast.makeText(StorageExActivity.this, "Image & Text Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}