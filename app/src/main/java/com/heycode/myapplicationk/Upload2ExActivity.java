package com.heycode.myapplicationk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Upload2ExActivity extends AppCompatActivity {
    ImageView upload_image2;
    TextView upload_text2;
    Button upload_button2;
    FirebaseStorage mStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2_ex);

        upload_image2 = findViewById(R.id.upload_img2);
        upload_text2 = findViewById(R.id.upload_txt2);
        upload_button2 = findViewById(R.id.upload_btn2);
        mStorage = FirebaseStorage.getInstance();

        upload_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        upload_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_image2.setDrawingCacheEnabled(true);
                upload_image2.buildDrawingCache();
                Bitmap bitmap = upload_image2.getDrawingCache();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                upload_image2.setDrawingCacheEnabled(false);
                byte[] bytes = byteArrayOutputStream.toByteArray();

                //saving in mentioned dir
                String path = "firstFolder/"+ UUID.randomUUID()+".png";
                StorageReference reference = mStorage.getReference(path);

                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setCustomMetadata("text",upload_text2.getText().toString()).build();
                UploadTask uploadTask = reference.putBytes(bytes,metadata);//image & text
                upload_button2.setEnabled(false);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        upload_button2.setEnabled(true);
                        Task<Uri> url = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        upload_text2.setText(url.toString());


                        Toast.makeText(Upload2ExActivity.this, "Image & Text Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK){
            if (data.getData() != null){
                Uri uri = data.getData();
                upload_image2.setImageURI(uri);

            }
        }
    }
}