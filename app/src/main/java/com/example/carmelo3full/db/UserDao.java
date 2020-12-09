package com.example.carmelo3full.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("SELECT uid FROM User WHERE name=:name")
    int getId(String name);

    @Query("SELECT * FROM User WHERE phone_number= :phone")
    User getFromPhone(String phone);

    @Query("SELECT phone_number FROM User WHERE name=:name")
    String getPhoneName(String name);

    @Insert
    void insertUser(User... users);

    @Query("UPDATE User set number=number+1 WHERE uid=:id")
    void updateNum(int id);

    @Query("DELETE FROM User WHERE uid = :id")
    void supUser(int id);

    @Query("DELETE FROM User WHERE name = :name")
    void delUser(String name);

    @Query("SELECT * FROM User WHERE uid= :id")
    User getIdData(int id);

    @Query("SELECT * FROM User WHERE name= :name")
    List<User> getName(String name);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllLive();

    @Query("UPDATE User SET name= :fn WHERE uid= :id")
    void updateName(String fn, int id);

    @Query("UPDATE User SET phone_number= :fn WHERE uid= :id")
    void updatePhone(String fn, int id);

    @Query("UPDATE User SET birth_date= :fn WHERE uid= :id")
    void updateBday(String fn, int id);

    @Query("UPDATE User SET number= :fn WHERE uid= :id")
    void updateNumber(String fn, int id);


}
