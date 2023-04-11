package com.example.planner.db.iConnection;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.planner.db.entity.Meeting;
import com.example.planner.db.entity.Payment;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PaymentDao {
    @Query("SELECT * FROM payment")
    List<Payment> getAll();

    @Query("SELECT * FROM payment WHERE date (:date)")
    List<Payment> getPaymentByDate(String date);

    @Query("DELETE FROM payment")
    void deleteAll();

    @Query("UPDATE payment SET title=:title, date=:date, amount=:amount, isPayment=:isPayment WHERE id=:id")
    void updatePayment(int id, String title, String date, float amount, boolean isPayment);

    @Insert
    void addPayment(Payment payment);

    @Delete
    void deletePayment(Payment payment);
}
