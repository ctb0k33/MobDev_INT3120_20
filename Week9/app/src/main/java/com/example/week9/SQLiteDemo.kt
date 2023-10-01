package com.example.week9

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.activity.ComponentActivity

class SQLiteDemo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReadDb()
    }
    fun ReadDb(){
        var feedReader: FeedReaderDbHelper = FeedReaderDbHelper(this)
        var db: SQLiteDatabase = feedReader.getReadableDatabase()
        Log.d("Database", "ReadDb:${db} ")

        var projection: List<String> = listOf(
            BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        )

        var selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " =?"
        var selectionArgs: List<String> = listOf("My Title")
        val sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC"

        var cursor: Cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
            projection.toTypedArray(),                               // The columns to return
            selection,                                // The columns for the WHERE clause
            selectionArgs.toTypedArray(),                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
        );
    }

    fun WriteDB(){
        var feedReader: FeedReaderDbHelper = FeedReaderDbHelper(this)
        var db: SQLiteDatabase = feedReader.writableDatabase
        var value:ContentValues = ContentValues()
        value.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,"title")
        value.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,"subTitle")

        var newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,value)

    }





}