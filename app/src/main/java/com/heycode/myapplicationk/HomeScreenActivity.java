package com.heycode.myapplicationk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {
    Button save_data;
    EditText phone, address, name, regNo;
    FirebaseAuth mFirebaseAuth;
    public DatabaseReference mDatabaseReference;
    UserAccount mUserAccount;
    ListView mListView;
    ArrayList<String> mArrayList;
    ArrayAdapter<String> mArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Screen");
        setSupportActionBar(toolbar);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();


        phone = findViewById(R.id.data_1);//phone
        address = findViewById(R.id.data_2);//Address
        name = findViewById(R.id.data_3);//Name
        regNo = findViewById(R.id.data_4);//Registration No
        mListView = findViewById(R.id.list_view);
        save_data = findViewById(R.id.save_data);

        mArrayList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<>(HomeScreenActivity.this, android.R.layout.simple_list_item_1, mArrayList);
        mListView.setAdapter(mArrayAdapter);



        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == save_data) {
                    saveData();
                    Toast.makeText(HomeScreenActivity.this, "Data Saved !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeScreenActivity.this, "Something is Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Reading data from database
    public void readData(View view){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot != null){
                    String s = "";
                    Iterable<DataSnapshot> iterable = snapshot.getChildren();

                    for(DataSnapshot ds:iterable){
                        Toast.makeText(HomeScreenActivity.this, ""+ds.getValue(), Toast.LENGTH_SHORT).show();
                        s = s+ds.getValue();
                    }
                    mArrayList.add(s);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Saving Data with SAVE BUTTON
    public void saveData() {
        String phone1 = phone.getText().toString();
        String address1 = address.getText().toString();
        String name1 = name.getText().toString();
        String regNo1 = regNo.getText().toString();

        mUserAccount = new UserAccount(name1, phone1, address1, regNo1);
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        mDatabaseReference.child(user.getUid()).child(name1).setValue(mUserAccount);//need an object of a
    }


    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeScreenActivity.this, SigninActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.goto_storage:startActivity(new Intent(HomeScreenActivity.this,StorageExActivity.class));
                return true;
            case R.id.goto_storage2:startActivity(new Intent(HomeScreenActivity.this,Upload2ExActivity.class));
                return true;
            case R.id.goto_animation:startActivity(new Intent(HomeScreenActivity.this, AnimationActivity.class));
                return true;

        }
        return false;
    }
}
