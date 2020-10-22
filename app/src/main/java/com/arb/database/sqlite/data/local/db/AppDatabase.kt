package com.arb.database.sqlite.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "test.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_TEAM = "team"
        const val TEAM_ID = "id"
        const val TEAM_NAME = "name"
        const val TEAM_GROUND = "ground"

        const val TABLE_PLAYER = "player"
        const val PLAYER_ID = "id"
        const val PLAYER_NAME = "name"
        const val PLAYER_TEAM_ID = "tame_id"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_TEAM(" +
                    "$TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$TEAM_NAME VARCHAR," +
                    "$TEAM_GROUND VARCHAR" +
                    ")"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_PLAYER(" +
                    "$PLAYER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$PLAYER_NAME VARCHAR," +
                    "$PLAYER_TEAM_ID INTEGER," +
                    "FOREIGN KEY($PLAYER_TEAM_ID) REFERENCES $TABLE_TEAM($TEAM_ID))"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_TEAM")

        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLAYER")

        onCreate(db)

    }


}