package com.example.planner.db.entity;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import com.example.planner.db.base.PaymentDataBase;
import com.example.planner.db.iConnection.PaymentDao;
import com.example.planner.interfaceCollection.IFormat;

import java.util.List;

@Entity
public class Payment implements Comparable<Payment> {
    private static PaymentDataBase db;

    public Payment(String title, String date, float amount, boolean isPayment)
    {
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.isPayment = isPayment;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "amount")
    public float amount;
    @ColumnInfo(name = "isPayment")
    public boolean isPayment;


    @Override
    public int compareTo(Payment payment) {
        return this.date.compareTo(payment.date);
    }

    private static void openDB(Context context) {
        if (db == null) db = Room.databaseBuilder(context, PaymentDataBase.class, "PaymentDataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public static void closeDB() {
        db.close();
        db = null;
    }

    public static List<Payment> db_getAllPayments(Context context) {
        openDB(context);
        PaymentDao paymentDao = db.paymentDao();
        closeDB();

        return paymentDao.getAll();
    }

    public static void db_deletePayment(Payment payment, Context context) {
        openDB(context);
        PaymentDao paymentDao = db.paymentDao();
        paymentDao.deletePayment(payment);
        closeDB();
    }

    public static void db_addPayment(Payment payment, Context context) {
        openDB(context);
        PaymentDao paymentDao = db.paymentDao();
        paymentDao.addPayment(payment);
        closeDB();
    }

    public static void db_deleteAll(Context context) {
        openDB(context);
        PaymentDao paymentDao = db.paymentDao();
        paymentDao.deleteAll();
        closeDB();
    }

    public static void db_updatePayment(int id, String title, String date, float amount, boolean isPayment, Context context) {
        openDB(context);
        PaymentDao paymentDao = db.paymentDao();
        paymentDao.updatePayment(id, title, date, amount, isPayment);
        closeDB();
    }


}
