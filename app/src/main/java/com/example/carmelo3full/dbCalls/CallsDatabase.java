package com.example.carmelo3full.dbCalls;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserCalls.class}, version = 2, exportSchema = false)
public abstract class CallsDatabase extends RoomDatabase {

    public abstract CallsDao userDao();

    private static CallsDatabase INSTANCE;

    public static CallsDatabase getDbInstance(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CallsDatabase.class, "DB_NAME2")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }



}
