package com.c0767722.contact_monika_c0767722_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c0767722.contact_monika_c0767722_android.adapter.ContactAdapter;
import com.c0767722.contact_monika_c0767722_android.database.UserDb;
import com.c0767722.contact_monika_c0767722_android.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.rvlistCustomers)
    RecyclerView rvlistCustomers;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.txtEmail)
    EditText txtEmail;
    @InjectView(R.id.conicon)
    ImageView conicon;
    @InjectView(R.id.rel1)
    RelativeLayout rel1;
    ActionBar actionBar;
    ContactAdapter contactAdapter;

    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        final UserDb userDb = UserDb.getInstance( this );
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("List Of Contacts");
        actionBar.show();
        //Add Customer Button Clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iIntent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(iIntent);

            }
        });
        contactAdapter = new ContactAdapter(this);

        contactList = userDb.daoObject().getDefault();

        userDb.daoObject().getUserDetails().observe(this,
                new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(List<Contact> contacts) {
                        contactList = contacts;
                        contactAdapter.setContactList(contactList);
                        contactAdapter.notifyDataSetChanged();
                    }
                });

        contactAdapter.setContactList(contactList);
        rvlistCustomers.setAdapter(contactAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvlistCustomers.setLayoutManager(linearLayoutManager);

        contactAdapter.notifyDataSetChanged();

    }
}