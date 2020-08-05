package com.cos.contactsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.cos.contactsapp.adapter.ContactAdapter;
import com.cos.contactsapp.db.ContactAppDatabase;
import com.cos.contactsapp.db.model.Contact;
import com.cos.contactsapp.service.ContactService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ContactAdapter adapter;
    private FloatingActionButton fab;
    private CircleImageView ivProfile;

    private ContactAppDatabase contactAppDatabase;
    private ContactService contactService;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB 관련
        contactAppDatabase = Room.databaseBuilder(getApplicationContext(), ContactAppDatabase.class, "ContactDB")
                .allowMainThreadQueries()
                .build();
        contactService = new ContactService(contactAppDatabase.contactRepository());

        initData();
        initObject();
        initDesign();
        initListener();
    }

    private void initData(){
        contacts = contactService.연락처전체보기();
    }

    private void initObject(){
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view_contacts);
        fab = findViewById(R.id.fab);
        ivProfile = findViewById(R.id.iv_profile);
        adapter = new ContactAdapter(this, contacts);
    }

    private void initDesign(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Contact App");

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContactDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addContactDialog(){
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_contact, null);

        final EditText etName = dialogView.findViewById(R.id.name);
        final EditText etEmail = dialogView.findViewById(R.id.email);

        // 갤러리 사진 가져오기
        ivProfile = dialogView.findViewById(R.id.iv_profile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToAlbum();
            }
        });

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("연락처 등록");
        dlg.setView(dialogView);
        dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createContact(etName.getText().toString(), etEmail.getText().toString());
            }
        });
        dlg.setNegativeButton("닫기", null);
        dlg.show();
    }

    private void createContact(String name, String email) {
        long contactId = contactService.연락처등록(new Contact(name, email));
        // if 해서 contactId가 0이 아닐 때 동작하게 해야 함
        Contact contact = contactService.연락처상세보기((long)contactId);
        adapter.addItem(contact);

    }
}

