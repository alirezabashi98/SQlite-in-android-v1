package com.arb.database.sqlite.data.local.db.dao

import com.arb.database.sqlite.data.local.db.AppDatabase
import com.arb.database.sqlite.data.model.Player

class PlayerDao(appDatabase: AppDatabase) : BaseDao<Player>(appDatabase) {

    override fun save(entity: Player): Boolean {

        val db = appDatabase.writableDatabase

        contentValues.clear()
        contentValues.put(AppDatabase.PLAYER_NAME, entity.name)
        contentValues.put(AppDatabase.PLAYER_TEAM_ID, entity.team_id)

        val result = db.insert(AppDatabase.TABLE_PLAYER, null, contentValues)
        db.close()

        return (result > 0)

    }

    override fun save(id: String, entity: Player): Boolean {

        val db = appDatabase.writableDatabase

        contentValues.clear()
        contentValues.put(AppDatabase.PLAYER_NAME, entity.name)
        contentValues.put(AppDatabase.PLAYER_TEAM_ID, entity.team_id)

        val result = db.update(AppDatabase.TABLE_PLAYER, contentValues, "id = ?", arrayOf(id))
        db.close()

        return (result > 0)

    }

    override fun findAll(): List<Player> {

        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TABLE_PLAYER}"
        val cursor = db.rawQuery(query, null)

        data.clear()
        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.PLAYER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.PLAYER_NAME))
                val teamId = cursor.getInt(cursor.getColumnIndex(AppDatabase.PLAYER_TEAM_ID))
                data.add(Player(id.toLong(), name, teamId.toLong()))

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return data

    }

    override fun find(columnName: String, columnValue: String): List<Player> {

        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TABLE_PLAYER} WHERE $columnName = ?"
        val cursor = db.rawQuery(query, arrayOf(columnValue))

        data.clear()
        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.PLAYER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.PLAYER_NAME))
                val teamId = cursor.getInt(cursor.getColumnIndex(AppDatabase.PLAYER_TEAM_ID))
                data.add(Player(id.toLong(), name, teamId.toLong()))

            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return data

    }

    override fun delete(id: String): Boolean {

        val db = appDatabase.writableDatabase
        val result =
            db.delete(AppDatabase.TABLE_PLAYER, "${AppDatabase.PLAYER_ID} = ?", arrayOf(id))
        db.close()

        return result > 0

    }
}