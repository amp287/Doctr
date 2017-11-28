package com.group.poop.doctr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UserHome extends AppCompatActivity {

    private Button mSignOut;

    private Button mSelectImage;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;

    private ProgressDialog mProgress;
    private ImageView mProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSignOut = findViewById(R.id.signOut);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserHome.this, LoginPage.class);
                startActivity(intent);
            }
        });

        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = (Button) findViewById(R.id.selectImage);

        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        mProfileView = (ImageView) findViewById(R.id.profileView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            mProgress.setMessage("Uploading Image");
            mProgress.show();

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgress.dismiss();

                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                    Picasso.with(UserHome.this).load(downloadUri).fit().centerCrop().into(mProfileView);

                    Toast.makeText(UserHome.this, "Upload Done", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
