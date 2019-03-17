package com.androchef.kisansms.db


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.androchef.kisansms.pojo.response.Message

class SqlLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "sent_sms_db"
        const val DATABASE_VERSION = 1
        private const val TABLE_SMS = "sent_sms"
        private const val KEY_ID = "id"
        private const val KEY_USER_NAME = "username"
        private const val KEY_USER_PHONE = "userphone"
        private const val KEY_TIME = "time"
        private const val KEY_OTP = "otp"
        private const val SQL_TABLE_USERS = (" CREATE TABLE " + TABLE_SMS
                + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TIME + " LONG, "
                + KEY_OTP + " TEXT, "
                + KEY_USER_NAME + " TEXT, "
                + KEY_USER_PHONE + " TEXT"
                + " ) ")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(" DROP TABLE IF EXISTS $TABLE_SMS")
    }

    fun addSentMessage(message: Message) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USER_NAME, message.name)
        values.put(KEY_OTP,message.otp)
        values.put(KEY_TIME,message.time)
        values.put(KEY_USER_PHONE,message.phone)
        val todo_id = db.insert(TABLE_SMS, null, values)
    }

    fun getAllMessageList() : MutableList<Message>?{
        val cursor = this.writableDatabase.rawQuery("select * from $TABLE_SMS ORDER BY $KEY_TIME DESC", null)
        val listOfMessages : MutableList<Message> ? = mutableListOf()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val message = Message()
                message.name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME))
                message.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                message.otp = cursor.getString(cursor.getColumnIndex(KEY_OTP))
                message.phone = cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE))
                message.time = cursor.getLong(cursor.getColumnIndex(KEY_TIME))
                listOfMessages?.add(message)
                cursor.moveToNext()
            }
        }
        return listOfMessages
    }
}
