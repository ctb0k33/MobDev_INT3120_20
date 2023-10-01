package com.example.week9

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, "SQLite Demo", null, 1) {

    companion object {
        private var SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" + "${BaseColumns._ID} INTEGER PRIMARY KEY," + "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} TEXT," + "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Add code for database schema upgrades if needed
    }
}
