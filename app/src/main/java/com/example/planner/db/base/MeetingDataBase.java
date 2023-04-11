package com.example.planner.db.base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.planner.db.entity.Meeting;
import com.example.planner.db.iConnection.MeetingDao;

@Database(entities = {Meeting.class}, version = 1 ,exportSchema = false)
public abstract class MeetingDataBase extends RoomDatabase {
    public abstract MeetingDao meetingDao();

}
