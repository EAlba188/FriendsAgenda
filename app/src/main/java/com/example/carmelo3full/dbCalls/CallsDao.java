package com.example.carmelo3full.dbCalls;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.carmelo3full.db.User;

import java.util.List;

@Dao
public interface CallsDao {


    @Query("SELECT * FROM UserCalls WHERE phone_number= :phone")
    UserCalls getFromPhone(String phone);

    @Insert
    void insertUser(UserCalls... userCalls);

    @Query("DELETE FROM UserCalls WHERE uid = :id")
    void supUser(int id);

    @Query("SELECT * FROM UserCalls WHERE uid= :id")
    UserCalls getIdData(int id);


    @Query("SELECT * FROM UserCalls")
    LiveData<List<UserCalls>> getAllLive();


}
