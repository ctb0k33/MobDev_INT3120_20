package com.example.week10

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class ProductsProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "com.example.week10"
        const val URL = "content://$PROVIDER_NAME/products"
        val CONTENT_URI = Uri.parse(URL)

        private val values: HashMap<String, String>? = null

        private var db: SQLiteDatabase? = null

        const val id = "id"
        const val name = "name"
        const val price = "price"
        const val uriCode = 1
        var uriMatcher: UriMatcher? = null

        const val DATABASE_NAME = "ProductDB"
        const val TABLE_NAME = "products"
        var DATABASE_VERSION = 1

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher!!.addURI(PROVIDER_NAME, "products", uriCode)

            // to access a particular row
            // of the table
            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "products/*",
                uriCode
            )
        }

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher?.match(uri)) {
            uriCode ->
                count = db!!.delete(TABLE_NAME, selection, selectionArgs)

            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher?.match(uri)) {
            uriCode ->
                "vnd.android.cursor.dir/products"

            else -> {
                throw IllegalArgumentException("Unsupported URI: $uri")
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowID = db?.insert(TABLE_NAME, "", values)
        if (rowID!! > 0) {
            val tempUri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(tempUri, null)
            return tempUri
        }
        throw SQLiteException("Failed to add a record into $uri")
    }

    override fun onCreate(): Boolean {
        val dbHelper = MyProductDatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = TABLE_NAME

        when (uriMatcher?.match(uri)) {
            uriCode -> queryBuilder.projectionMap = values
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = id
        }

        val cursor =
            queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)

        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        var count = 0
        when (uriMatcher?.match(uri)) {
            uriCode ->
                count = db!!.update(TABLE_NAME, values, selection, selectionArgs)

            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    private class MyProductDatabaseHelper(var context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


        //sql query to create a new table
        val CREATE_DB_TABLE =
            (" CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price NUMBER NOT NULL);")

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}