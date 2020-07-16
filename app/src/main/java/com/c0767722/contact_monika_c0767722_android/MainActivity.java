package com.c0767722.contact_monika_c0767722_android;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
}