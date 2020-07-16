package com.c0767722.contact_monika_c0767722_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.c0767722.contact_monika_c0767722_android.database.UserDb;
import com.c0767722.contact_monika_c0767722_android.model.Contact;
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
    ActionBar actionBar;
    Contact contactEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        ButterKnife.inject(this);
        contactEdit = getIntent().getParcelableExtra("contactData");


        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        if (contactEdit != null) {
            actionBar.setTitle("Edit Contact");
        } else {
            actionBar.setTitle("Add Contact");
        }

        actionBar.show();

        if (contactEdit != null) {
            txtFname.setText(contactEdit.getFirstName());
            txtLname.setText(contactEdit.getLastName());
            txtEmail.setText(contactEdit.getEmail());
            txtPhoneNumber.setText(contactEdit.getPhone());
            txtAddress.setText(contactEdit.getAddress());

        }

        btnssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = txtFname.getText().toString();
                String lName = txtLname.getText().toString();
                String email = txtEmail.getText().toString();
                String address = txtAddress.getText().toString();
                String phone = txtPhoneNumber.getText().toString();

                //Object for room Database;
                UserDb userDb = UserDb.getInstance(v.getContext());

                if (contactEdit != null) {
                    contactEdit.setFirstName(fName);
                    contactEdit.setLastName(lName);
                    contactEdit.setEmail(email);
                    contactEdit.setAddress(address);
                    contactEdit.setPhone(phone);
                    userDb.daoObject().updateUser(contactEdit);
                    showAlert("Contact Updated SuccessFully");

                } else {
                    if (validation()) {
                        Contact contact = new Contact(fName, lName, email, address, phone);
                        userDb.daoObject().insertUser(contact);
                        showAlert("Contact Added SuccessFully");

                    }
                }
                hideKeyboard(AddUserActivity.this);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setIcon(R.drawable.ic_action_alerts);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private boolean validation() {
        if (txtFname.getText().toString().trim().length() == 0) {
            txtFname.setError("Please enter First Name");
            txtFname.requestFocus();
            return false;
        } else if (txtLname.getText().toString().trim().length() == 0) {
            txtLname.setError("Please Enter Last name");
            txtLname.requestFocus();
            return false;
        } else if (txtEmail.getText().toString().trim().length() == 0) {
            txtEmail.setError("Please enter Email Address");
            txtEmail.requestFocus();
            return false;
        } else if (!isValidEmail(txtEmail.getText().toString())) {
            txtEmail.setError("Please enter Valid Email");
            txtEmail.requestFocus();
            return false;
        } else if (txtPhoneNumber.getText().toString().trim().length() == 0) {
            txtEmail.setError("Please enter Phone Number");
            txtAddress.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}