package com.example.carmelo3full.dbCalls;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class UserCalls {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "idAmigo")
    public int idAmigo;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "fecha")
    public String fecha;

}
