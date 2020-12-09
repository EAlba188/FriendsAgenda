package com.example.carmelo3full.dbCalls;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.carmelo3full.db.ThreadExecutor;

import java.util.List;

public class RepositoryCalls {

    private CallsDatabase repositoryDB;
    private LiveData<List<UserCalls>> liveUserList;
    private CallsDao callsDao;

    public RepositoryCalls(Context context){
        repositoryDB = CallsDatabase.getDbInstance(context);
        callsDao = repositoryDB.userDao();
        liveUserList = callsDao.getAllLive();
    }

    public LiveData<List<UserCalls>> getLiveUserList(){
        return liveUserList;
    }

    public UserCalls getFromPhone(String phone){
        return callsDao.getFromPhone(phone);
    }

    public void insert(UserCalls user){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                callsDao.insertUser(user);
            }
        });
    }

    public void delete(int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                callsDao.supUser(id);
            }
        });
    }
}
