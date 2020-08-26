package com.songlan.dataapplication.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createBookTable = "create table Book( "+
            "id integer primary key autoincrement, "+
            "name text, "+
            "author text, "+
            "page integer, "+
            "price real)"

    private val createCategoryTable = "create table Category( "+
            "id integer primary key autoincrement, "+
            "category_name text, "+
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBookTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion < 1){
            db?.execSQL(createBookTable)
        }
        if (oldVersion < 2){
            db?.execSQL(createCategoryTable)
        }
    }
}