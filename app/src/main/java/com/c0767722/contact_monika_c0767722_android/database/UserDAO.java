package com.c0767722.contact_monika_c0767722_android.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.c0767722.contact_monika_c0767722_android.model.Contact;

import java.util.List;

@Dao
public interface UserDAO {


    @Insert
    void insertUser(Contact person);

    @Delete
    void deleteUser(Contact person);

    @Update
    void updateUser(Contact person);

    @Query("Select count(id) from contact")
    Integer countNoOfUsers();

    @Query("Select count(id) from contact")
    LiveData<Integer> countUpdatedUser();

    @Query("Select * from contact")
    LiveData<List<Contact>> getUserDetails();

    @Query("Select * from contact")
    List<Contact> getDefault();
}
