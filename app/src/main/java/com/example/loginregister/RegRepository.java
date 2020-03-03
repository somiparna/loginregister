package com.example.loginregister;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RegRepository {

    /*
    private RegisterDao registerDao;
    private List<PersonalDetails> allRegisters;

    public RegRepository(Application application){
        RegisterDatabase database = RegisterDatabase.getInstance(application);
        registerDao = database.registerDao();
        allRegisters= registerDao.getAllEntries();
    }

    public void insert(PersonalDetails register){
        new InsertRegisterAsyncTask(registerDao).execute(register);
    }
    public void update(PersonalDetails register){
        new UpdateRegisterAsyncTask(registerDao).execute(register);
    }
    public void delete(PersonalDetails register){
        new DeleteRegisterAsyncTask(registerDao).execute(register);
    }
    public void deleteAllEntries(PersonalDetails register){
        new DeleteAllRegisterAsyncTask(registerDao).execute();
    }
    public List<PersonalDetails>getAllRegisters(){
        return allRegisters;
    }

    private static class InsertRegisterAsyncTask extends AsyncTask<PersonalDetails,Void,Void>{
        private RegisterDao registerDao;

        private InsertRegisterAsyncTask(RegisterDao registerDao){
            this.registerDao = registerDao;
        }

        @Override
        protected Void doInBackground(PersonalDetails... registers) {
            registerDao.insert(registers[0]);
            return null;
        }
    }

    private static class UpdateRegisterAsyncTask extends AsyncTask<PersonalDetails,Void,Void>{
        private RegisterDao registerDao;

        private UpdateRegisterAsyncTask(RegisterDao registerDao){
            this.registerDao = registerDao;
        }

        @Override
        protected Void doInBackground(PersonalDetails... registers) {
            registerDao.update(registers[0]);
            return null;
        }
    }
    private static class DeleteRegisterAsyncTask extends AsyncTask<PersonalDetails,Void,Void>{
        private RegisterDao registerDao;

        private DeleteRegisterAsyncTask(RegisterDao registerDao){
            this.registerDao = registerDao;
        }

        @Override
        protected Void doInBackground(PersonalDetails... registers) {
            registerDao.delete(registers[0]);
            return null;
        }
    }

    private static class DeleteAllRegisterAsyncTask extends AsyncTask<Void,Void,Void> {
        private RegisterDao registerDao;

        private DeleteAllRegisterAsyncTask(RegisterDao registerDao) {
            this.registerDao = registerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            registerDao.deleteAllEntries();
            return null;
        }
    }
    */
}
