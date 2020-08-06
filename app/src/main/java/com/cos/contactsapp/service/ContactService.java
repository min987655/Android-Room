package com.cos.contactsapp.service;

import android.util.Log;

import com.cos.contactsapp.Repository.ContactRepository;
import com.cos.contactsapp.db.model.Contact;

import java.util.ArrayList;
import java.util.List;

// 웹과는 다름, 나 혼자만 접근하기 때문에 싱글톤 굳이 안만들어도 됨(웹은 동시에 여러면 접근할 수 있지만)
public class ContactService {

    private static final String TAG = "ContactService";
    
    public ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public long 연락처등록(Contact contact) {
        return contactRepository.save(contact);
    }

    public long 연락처수정(Contact contact) {
        Log.d(TAG, "연락처수정: ");
        return contactRepository.update(contact);
    }

    public Contact 연락처상세보기(long contactId){
        return contactRepository.findById(contactId);
    }

    public long 연락처삭제(long contactId) {
        return contactRepository.delete(contactId);
    }

    public long 연락처전체삭제() {
        return contactRepository.deleteAll();
    }

    public List<Contact> 연락처전체보기() {
         return contactRepository.findAll();
    }
}
