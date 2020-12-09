package com.example.carmelo3full.db;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.carmelo3full.dbCalls.RepositoryCalls;
import com.example.carmelo3full.dbCalls.UserCalls;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Repository {

    private AppDatabase repositoryDB;
    private LiveData<List<User>> liveUserList;
    private UserDao userDao;
    private Context context;

    public Repository(Context context){
        repositoryDB = AppDatabase.getDbInstance(context);
        userDao = repositoryDB.userDao();
        liveUserList = userDao.getAllLive();
        this.context = context;
    }

    public LiveData<List<User>> getLiveUserList(){
        return liveUserList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getFromPhone(String phone){
        Log.v("zxc", phone);
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {

                User user = userDao.getFromPhone(phone);

                if(user!=null){
                    userDao.updateNum(user.uid);
                    RepositoryCalls repositoryCalls = new RepositoryCalls(context);
                    UserCalls userCalls = new UserCalls();
                    userCalls.idAmigo=user.uid;
                    repositoryCalls.insert(userCalls);
                }

            }
        });
    }

    public void updateNum(int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateNum(id);
            }
        });
    }

    public void insert(User user){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
    }

    public int getId(String name){
        return userDao.getId(name);
    }

    public int getName(String name){ //COMO PONERLO EN HILO???
        List<User> li;
        li = userDao.getName(name);
        return li.size();
    }

    public void delete(int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.supUser(id);
            }
        });
    }

    public void updateFirstName(String arg, int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateName(arg, id);
            }
        });
    }

    public void updatePhone(String arg, int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updatePhone(arg, id);
            }
        });
    }

    public void updateBday(String arg, int id){                     //COGER EL NUMERO Y SUMAR UNO
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateBday(arg, id);
            }
        });
    }

    public void updateNumber(String arg, int id){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateNumber(arg, id);
            }
        });
    }

    public void delUser(String name){
        ThreadExecutor.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delUser(name);
            }
        });
    }



}
