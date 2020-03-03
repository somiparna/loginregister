package com.example.loginregister;

//import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.net.PasswordAuthentication;
import java.util.List;

@Dao
public interface RegisterDao {
        @Insert
        void insert(PersonalDetails details);

        @Update
        void update(PersonalDetails details);

        @Delete
        void delete(PersonalDetails details);

        @Query("DELETE FROM PersonalDetails")
        void deleteAllEntries();

        @Query("SELECT * FROM PersonalDetails ")
        List<PersonalDetails> getAllEntries();

        @Query("SELECT * FROM PersonalDetails WHERE PersonalDetails.password = :Password AND PersonalDetails.emailID = :EmailId")
        List<PersonalDetails> getEntry(String Password,String EmailId);
        //int login(String Password,String EmailId);
        //int getCount();

        @Query("SELECT * FROM PersonalDetails WHERE PersonalDetails.emailID = :EmailId")
        int DuplicateEmail(String EmailId);

//        @Query("SELECT
//                count(*)!=0
//        FROM
//        PersonalDetails
//                JOIN
//        pragma_table_info(m.name) AS p
//        Where
//        p.pk=1")
//        public abstract bool findPrimaryKeyExistsInDB();
       /* @Query("SELECT * FROM IssueTable ORDER BY issueId DESC")
        List<IssueTable> getAllIssue();*/
    }

