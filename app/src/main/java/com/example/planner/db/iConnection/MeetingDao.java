package com.example.planner.db.iConnection;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.planner.db.entity.Meeting;

import java.util.List;

@Dao
public interface MeetingDao {
    @Query("SELECT * FROM meeting")
    List<Meeting> getAll();

    @Query("SELECT * FROM meeting WHERE date (:date)")
    List<Meeting> getMeetingByDate(String date);

    @Query("DELETE FROM meeting")
    void deleteAll();

    @Query("UPDATE meeting SET title=:title, date=:date, information=:information WHERE id=:id")
    void updateMeeting(int id, String title, String date, String information);

    @Insert
    void addMeeting(Meeting meeting);

    @Delete
    void deleteMeeting(Meeting meeting);

}
