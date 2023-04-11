package com.example.planner.db.entity;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import com.example.planner.db.base.MeetingDataBase;
import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.iConnection.MeetingDao;
import com.example.planner.interfaceCollection.IFormat;

import java.util.List;

@Entity
public class Meeting implements Comparable<Meeting> {
    private static MeetingDataBase db;

    public Meeting(String title, String date, String information)
    {
        this.title = title;
        this.date = date; // format -> dd.MM.yyyy
        this.information = information;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "information")
    public String information;

    @Override
    public int compareTo(Meeting meeting) {
        Integer thisMeeting = IFormat.DateInInt(this.date);
        Integer anotherMeeting = IFormat.DateInInt(meeting.date);
        return this.date.compareTo(meeting.date);
    }

    public static int getDateDif(Meeting m1, Meeting m2) {
        return dateValue(m1) - dateValue(m2);
    }

    public static int getDateDif(Meeting meeting) {
        Meeting now = new Meeting("", IFormat.CurrentDate(), "");

        return dateValue(meeting) - dateValue(now);
    }

    private static int dateValue(Meeting meeting) {
        String[] val = meeting.date.split("\\.");

        int dayValue = Integer.parseInt(val[0]);
        int monthValue = Integer.parseInt(val[1]) * 31;
        int yearValue = Integer.parseInt(val[2]) * 31 * 12;

        return dayValue + monthValue + yearValue;
    }

    private static void openDB(Context context) {
        if (db == null) db = Room.databaseBuilder(context, MeetingDataBase.class, "MeetingDataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();

    }

    public static void closeDB() {
        db.close();
        db = null;
    }

    public static List<Meeting> db_getAllMeetings(Context context) {
        openDB(context);
        MeetingDao meetingDao = db.meetingDao();
        closeDB();

        return meetingDao.getAll();
    }

    public static void db_deleteMeeting(Meeting meeting, Context context) {
        openDB(context);
        MeetingDao meetingDao = db.meetingDao();
        meetingDao.deleteMeeting(meeting);
        closeDB();
    }

    public static void db_addMeeting(Meeting meeting, Context context) {
        openDB(context);
        MeetingDao meetingDao = db.meetingDao();
        meetingDao.addMeeting(meeting);
        closeDB();
    }

    public static void db_deleteAll(Context context) {
        openDB(context);
        MeetingDao meetingDao = db.meetingDao();
        meetingDao.deleteAll();
        closeDB();
    }

    public static void db_updateMeeting(int id, String title, String date, String information, Context context) {
        openDB(context);
        MeetingDao meetingDao = db.meetingDao();
        meetingDao.updateMeeting(id, title, date, information);
        closeDB();
    }

}
