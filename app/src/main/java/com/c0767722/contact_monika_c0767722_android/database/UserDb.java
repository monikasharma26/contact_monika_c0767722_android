package com.c0767722.contact_monika_c0767722_android.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.c0767722.contact_monika_c0767722_android.model.Contact;

@Database(entities = Contact.class,version = 1,exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public static final String DB_NAME = "db_user";

    private static UserDb uInstance;


    public static UserDb getInstance(Context context)
    {
        if(uInstance == null)
        {
            uInstance = Room.databaseBuilder(context.getApplicationContext(), UserDb.class, UserDb.DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return uInstance;
    }


    public abstract UserDAO daoObject();

}
