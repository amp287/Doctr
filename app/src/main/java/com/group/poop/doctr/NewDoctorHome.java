package com.group.poop.doctr;

import android.app.ProgressDialog;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class NewDoctorHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TestFragment.OnFragmentInteractionListener,
        PatientFragment.OnFragmentInteractionListener,
        OfferedAppointmentsFragment.OnFragmentInteractionListener,
        ConversationFragment.OnFragmentInteractionListener{

    private BottomNavigationView mBNV;

    // Text View
    private TextView userNameTextView;
    private TextView userEmailTextView;

    private ImageView mDocView;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private static final int GALLERY_INTENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mStorage = FirebaseStorage.getInstance().getReference();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize Name and Email
        userNameTextView = navigationView.getHeaderView(0).findViewById(R.id.userNameTextView);
        userEmailTextView = navigationView.getHeaderView(0).findViewById(R.id.userEmailTextView);

        // TODO - The .getDisplayName isn't returning a string value.
        User user = FireBaseAPI.getCurrentUser();
        if( user != null ) {
            String userName = user.getFirstName() + " " + user.getLastName();
            userNameTextView.setText(userName);
        }
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        userEmailTextView.setText(userEmail);

        mBNV = findViewById(R.id.doctor_bottom_navigation);
        mBNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                FragmentTransaction transaction;
                switch (item.getItemId()) {
                    case R.id.doctor_offered_appointments:
                        selected = OfferedAppointmentsFragment.newInstance();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, selected);
                        transaction.commit();
                        break;
                    case R.id.doctor_patients:
                        selected = PatientFragment.newInstance();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, selected);
                        transaction.commit();
                        break;
                    case R.id.doctor_messages:
                        selected = ConversationFragment.newInstance();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, selected);
                        transaction.commit();
                }
                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.frameLayout, selected);
                //transaction.commit();
                return true;
            }
        });
        mBNV.setSelectedItemId(R.id.doctor_offered_appointments);

        mProgress = new ProgressDialog(this);
        mDocView = navigationView.getHeaderView(0).findViewById(R.id.docView);

        mDocView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_doctor_home, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.signOut) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(NewDoctorHome.this, LoginPage.class);
            startActivity(intent);
        } else if(id == R.id.editInfo){
            // TODO - A new activity needs to be created for this!

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.create_appt) {
        Intent intent = new Intent(NewDoctorHome.this, CreateAppointment.class);
        startActivity(intent);
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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

                    Picasso.with(NewDoctorHome.this).load(downloadUri).fit().centerCrop().into(mDocView);

                    Toast.makeText(NewDoctorHome.this, "Upload Done", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
