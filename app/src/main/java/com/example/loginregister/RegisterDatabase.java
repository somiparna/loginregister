package com.example.loginregister;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = PersonalDetails.class,version = 1, exportSchema = false)
public abstract class RegisterDatabase extends RoomDatabase {

    private static final String Db_name = "PersonalDetailsDb";
    private static RegisterDatabase instance;

    public  static synchronized RegisterDatabase getInstance(Context context){
        if(instance==null){
            instance = create(context);
        }
        return  instance;
    }

    private static RegisterDatabase create(Context context) {
        return Room.databaseBuilder(context,RegisterDatabase.class,Db_name).build();
    }

    public abstract RegisterDao getRegisterDao();
}
