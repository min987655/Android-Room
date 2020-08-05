package com.cos.contactsapp.Repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cos.contactsapp.db.model.Contact;

import java.util.List;

@Dao
public interface ContactRepository {

    // 삽입된 행의 Primary Key값을 long 형으로 return 해준다.
    // 삽입된 행이 여러건일 경우 List<Long> 형으로 return 해준다.
    // insert 된 데이터의 PK를 바로 리턴해 줌
    @Insert
    long save(Contact contact);

    // 수정된 행의 개수를 int 형으로 return 해준다.
    @Update
    int update(Contact contact);

    // 삭제된 행의 개수를 int 형으로 return 해준다.
    @Query("DELETE FROM contacts WHERE contact_id = :contactId")
    int delete(long contactId);

    @Query("DELETE FROM contacts")
    int deleteAll();

    @Query("SELECT * FROM contacts")
    List<Contact> findAll();

    @Query("SELECT * FROM contacts WHERE contact_id = :contactId ")
    Contact findById(long contactId);
}
