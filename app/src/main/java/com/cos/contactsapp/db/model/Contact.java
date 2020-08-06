package com.cos.contactsapp.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true) // 기본키 + 시퀀스
    @ColumnInfo(name="contact_id") // DB에 만들 이름 작성
    private long id;

    @ColumnInfo(name="contact_name")
    private String name;

    @ColumnInfo(name="contact_email")
    private String email;

    @ColumnInfo(name="contact_profile_url")
    private String profileURL;

    // Room은 생성자로 DB에 SET 함. 생성자는 하나만 Room이 읽어야 함.
    // 디비에 안넣을꺼라서 꼭 걸어주기
    @Ignore // 만들고 싶지 않은 필드를 제외시켜주는 어노테이션
    public Contact() { }

    @Ignore
    public Contact(long id, String name, String email, String profileURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileURL = profileURL;
    }

    @Ignore
    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Contact(String name, String email, String profileURL) {
        this.name = name;
        this.email = email;
        this.profileURL = profileURL;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

/**
 *  @Ignore // 엔티티에 지속하고 싶지 않은 필드나 생성자가 있는 경우 @Ignore를 사용한다.
 */
