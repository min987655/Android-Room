package com.cos.contactsapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cos.contactsapp.Repository.ContactRepository;
import com.cos.contactsapp.db.model.Contact;

@Database(entities = {Contact.class}, version = 2)
public abstract class ContactAppDatabase extends RoomDatabase {
    public abstract ContactRepository contactRepository();
}
