package com.cos.contactsapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.contactsapp.MainActivity;
import com.cos.contactsapp.R;
import com.cos.contactsapp.db.model.Contact;

import java.util.List;

// 어댑터 new 할 때 MainActivity 받음.
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static final String TAG = "ContactsAdapter";
    private MainActivity mainActivity;
    private List<Contact> contacts;

    public void addItems(List<Contact> contacts){
        this.contacts = contacts;
    }

    public void addItem(Contact contact){
        contacts.add(contact);
    }

    public ContactAdapter(MainActivity mainActivity, List<Contact> contacts) {
        this.mainActivity = mainActivity;
        this.contacts = contacts;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }

        void setItem(String name, String email){
            Log.d(TAG, "setItem: ");
            tvName.setText(name);
            tvEmail.setText(email);

        }
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, final int position) {
        // 컬렉션 증가 변화에만 반응함.
        final Contact contact = contacts.get(position);
        holder.setItem(contact.getName(), contact.getEmail());

        // 데이터 바인딩할 때 이벤트 달기
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainActivity.editContact(contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
