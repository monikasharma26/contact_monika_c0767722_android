package com.c0767722.contact_monika_c0767722_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.c0767722.contact_monika_c0767722_android.AddUserActivity;
import com.c0767722.contact_monika_c0767722_android.R;
import com.c0767722.contact_monika_c0767722_android.database.UserDb;
import com.c0767722.contact_monika_c0767722_android.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> contactList;
    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContactList(List<Contact> personsList) {
        this.contactList = personsList;
    }
    public List<Contact> getContactList() {
        return contactList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customerlist,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final  Contact contact = contactList.get(position);
        holder.personImg.setImageResource(R.drawable.emp);
        holder.deleteContact.setImageResource(R.drawable.del);
        holder.fullname.setText(contact.getFirstName() + " " +contact.getLastName());
        holder.email.setText(contact.getEmail());
        holder.phoneNo.setText(contact.getPhone());
        holder.address.setText(contact.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddUserActivity.class);
                intent.putExtra("contactData",position);
                context.startActivity(intent);
            }
        });
        holder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullname,email,phoneNo,address;
        ImageView deleteContact,personImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            fullname = itemView.findViewById(R.id.txtName);
            phoneNo = itemView.findViewById(R.id.txtPhone);
            address = itemView.findViewById(R.id.txtAdd);
            deleteContact = itemView.findViewById(R.id.imgDel);
            personImg = itemView.findViewById(R.id.imgAttraction);




        }
    }


    public void deleteContact(int position) {

        Contact person = contactList.get(position);
        UserDb userDb = UserDb.getInstance(getContext());
        userDb.daoObject().deleteUser(person);
        contactList.remove(position);
        notifyDataSetChanged();

    }
    public void showDialogForDelete(final int pos) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Are you Sure to Delete this contact");
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               deleteContact(pos);


            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog mAlertDialog = alertDialogBuilder.create();
        mAlertDialog.show();
    }
}
