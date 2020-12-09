package com.example.carmelo3full;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.carmelo3full.db.Repository;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.dbCalls.RepositoryCalls;
import com.example.carmelo3full.dbCalls.UserCalls;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


            Repository repository = new Repository(context);
            repository.getFromPhone(number);

        }


    }
}