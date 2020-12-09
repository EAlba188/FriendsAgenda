package com.example.carmelo3full.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.carmelo3full.db.Repository;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.dbCalls.RepositoryCalls;
import com.example.carmelo3full.dbCalls.UserCalls;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private RepositoryCalls repository2;
    private LiveData<List<User>> listLiveData;
    private LiveData<List<UserCalls>> listLiveData2;


    public ViewModel(@NonNull Application application){
        super(application);
        repository = new Repository(application);
        repository2 = new RepositoryCalls(application.getApplicationContext());
        listLiveData = repository.getLiveUserList();
        listLiveData2 = repository2.getLiveUserList();

    }



    public LiveData<List<User>> getListLiveData(){
        return listLiveData;
    }

    public LiveData<List<UserCalls>> getListLiveData2(){
        return listLiveData2;
    }

    public void insert(User user, Context context){
        repository.insert(user);
    }

    public void delete(int id, Context context){
        repository.delete(id);
    }

    public int getName(String name){
        return repository.getName(name);
    }

    public void updateName(String arg ,int id, Context context){
        repository.updateFirstName(arg, id);
    }

    public void updatePhone(String arg ,int id, Context context){
        repository.updatePhone(arg, id);
    }
    public int getId(String name){
        return repository.getId(name);
    }

    public void updateBday(String arg ,int id, Context context){
        repository.updateBday(arg, id);
    }



    public void updateNumber(String arg ,int id, Context context){
        repository.updateNumber(arg, id);
    }

    public void delUser(String name){
        repository.delUser(name);
    }

}
