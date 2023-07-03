package com.example.convidados.repository


import com.example.convidados.constants.DataBaseConstants
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class GuestDataBase(context: Context) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {

        companion object {
            private const val NAME = "questdb"
            private const val VERSION = 1
        }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE ${DataBaseConstants.GUEST.TABLE_NAME} (" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}