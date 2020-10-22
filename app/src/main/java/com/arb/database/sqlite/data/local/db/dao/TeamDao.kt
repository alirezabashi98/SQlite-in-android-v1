package com.arb.database.sqlite.data.local.db.dao

import com.arb.database.sqlite.data.local.db.AppDatabase
import com.arb.database.sqlite.data.model.Team

class TeamDao(appDatabase: AppDatabase) : BaseDao<Team>(appDatabase) {

    override fun save(entity: Team): Boolean {

        val db = appDatabase.writableDatabase

        contentValues.clear()
        contentValues.put(AppDatabase.TEAM_NAME, entity.name)
        contentValues.put(AppDatabase.TEAM_GROUND, entity.ground)

        val result = db.insert(AppDatabase.TABLE_TEAM, null, contentValues)
        db.close()

        return (result > 0)
    }

    override fun save(id: String, entity: Team): Boolean {

        val db = appDatabase.writableDatabase

        contentValues.clear()
        contentValues.put(AppDatabase.TEAM_NAME, entity.name)
        contentValues.put(AppDatabase.TEAM_GROUND, entity.ground)

        val result = db.update(AppDatabase.TABLE_TEAM, contentValues, "id = ? ", arrayOf(id))
        db.close()

        return (result > 0)

    }

    override fun findAll(): List<Team> {

        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TABLE_TEAM}"
        val cursor = db.rawQuery(query, null)

        data.clear()
        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.TEAM_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_NAME))
                val ground = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_GROUND))
                data.add(Team(id.toLong(), name, ground))

            } while (cursor.moveToNext())

        }
        cursor.close()
        db.close()

        return data

    }

    override fun find(columnName: String, columnValue: String): List<Team> {

        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TABLE_TEAM} WHERE $columnName = ?"
        val cursor = db.rawQuery(query, arrayOf(columnValue))

        data.clear()
        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.TEAM_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_NAME))
                val ground = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_GROUND))
                data.add(Team(id.toLong(), name, ground))

            } while (cursor.moveToNext())

        }
        cursor.close()
        db.close()

        return data

    }

    override fun delete(id: String): Boolean {

        val db = appDatabase.writableDatabase
        val result = db.delete(AppDatabase.TABLE_TEAM, "${AppDatabase.TEAM_ID} = ?", arrayOf(id))
        db.close()

        return result > 0

    }
}