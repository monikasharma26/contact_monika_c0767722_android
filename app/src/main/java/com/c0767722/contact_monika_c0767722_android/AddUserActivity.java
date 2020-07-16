package com.c0767722.contact_monika_c0767722_android;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddUserActivity extends AppCompatActivity {

    @InjectView(R.id.txtFname)
    EditText txtFname;
    @InjectView(R.id.emailicon)
    ImageView emailicon;
    @InjectView(R.id.rel1)
    RelativeLayout rel1;
    @InjectView(R.id.txtLname)
    EditText txtLname;
    @InjectView(R.id.icon)
    ImageView icon;
    @InjectView(R.id.rel2)
    RelativeLayout rel2;
    @InjectView(R.id.txtEmail)
    EditText txtEmail;
    @InjectView(R.id.conicon)
    ImageView conicon;
    @InjectView(R.id.rel3)
    RelativeLayout rel3;
    @InjectView(R.id.txtPhoneNumber)
    EditText txtPhoneNumber;
    @InjectView(R.id.idicon)
    ImageView idicon;
    @InjectView(R.id.rel4)
    RelativeLayout rel4;
    @InjectView(R.id.txtAddress)
    EditText txtAddress;
    @InjectView(R.id.idadd)
    ImageView idadd;
    @InjectView(R.id.rel5)
    RelativeLayout rel5;
    @InjectView(R.id.btnssave)
    MaterialButton btnssave;
    @InjectView(R.id.btnCancel)
    MaterialButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        ButterKnife.inject(this);
    }
}