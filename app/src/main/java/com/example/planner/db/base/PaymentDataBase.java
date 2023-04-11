package com.example.planner.db.base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.planner.db.entity.Payment;
import com.example.planner.db.iConnection.PaymentDao;


@Database(entities = {Payment.class}, version = 1, exportSchema = false)
public abstract class PaymentDataBase extends RoomDatabase {
    public abstract PaymentDao paymentDao();
}