package com.example.rajgiraseabhinavtask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.rajgiraseabhinavtask.model.MemberModel;

import java.util.ArrayList;
import java.util.List;

public class MemberDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "member_db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "members_table";

    public MemberDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "member_name TEXT, member_no TEXT, select_role TEXT, subscription_fee TEXT, " +
                "deposit_amount TEXT, loan_amount TEXT, member_gender TEXT, member_do_birth TEXT, " +
                "member_do_joining TEXT, marital_status TEXT, member_do_marriage TEXT,member_cast TEXT," +
                "member_religion TEXT, member_category TEXT, member_aadhar_no TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean addMembers(String member_name, String member_no, String select_role, String subscription_fee,
                              String deposit_amount, String loan_amount, String member_gender, String member_do_birth,
                              String member_do_joining, String marital_status, String member_do_marriage, String member_cast,
                              String member_religion, String member_category, String member_aadhar_no) {

        SQLiteDatabase sb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("member_name", member_name);
        contentValues.put("member_no", member_no);
        contentValues.put("select_role", select_role);
        contentValues.put("subscription_fee", subscription_fee);
        contentValues.put("deposit_amount", deposit_amount);
        contentValues.put("loan_amount", loan_amount);
        contentValues.put("member_gender", member_gender);
        contentValues.put("member_do_birth", member_do_birth);
        contentValues.put("member_do_joining", member_do_joining);
        contentValues.put("marital_status", marital_status);
        contentValues.put("member_do_marriage", member_do_marriage);
        contentValues.put("member_cast", member_cast);
        contentValues.put("member_religion", member_religion);
        contentValues.put("member_category", member_category);
        contentValues.put("member_aadhar_no", member_aadhar_no);

        long inserted = sb.insert(TABLE_NAME, null, contentValues);

        return inserted > 0;

    }


    public List<MemberModel> getAllMembers() {

        SQLiteDatabase sb = this.getReadableDatabase();
        List<MemberModel> allMembers = new ArrayList<>();

        Cursor cursor = sb.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String member_name = cursor.getString(1);
                String member_no = cursor.getString(2);
                String select_role = cursor.getString(3);
                String subscription_fee = cursor.getString(4);
                String deposit_amount = cursor.getString(5);
                String loan_amount = cursor.getString(6);
                String member_gender = cursor.getString(7);
                String member_do_birth = cursor.getString(8);
                String member_do_joining = cursor.getString(9);
                String marital_status = cursor.getString(10);
                String member_do_marriage = cursor.getString(11);
                String member_cast = cursor.getString(12);
                String member_religion = cursor.getString(13);
                String member_category = cursor.getString(14);
                String member_aadhar_no = cursor.getString(15);

                MemberModel memberModel = new MemberModel(id, member_name, member_no, select_role, subscription_fee,
                        deposit_amount, loan_amount, member_gender, member_do_birth, member_do_joining, marital_status,
                        member_do_marriage, member_cast, member_religion, member_category, member_aadhar_no);

                allMembers.add(memberModel);

            } while (cursor.moveToNext());
        }
        return allMembers;
    }

}
