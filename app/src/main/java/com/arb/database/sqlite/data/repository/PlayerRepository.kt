package com.arb.database.sqlite.data.repository

import com.arb.database.sqlite.data.local.db.dao.PlayerDao
import com.arb.database.sqlite.data.model.Player

class PlayerRepository(val playerDao: PlayerDao) {

    fun savePlayer(player: Player): Boolean = playerDao.save(player)

    fun findTeamById(columnName: String, columnValue: String): Player =
        playerDao.find(columnName, columnValue)[0]

    fun findTeamsByName(columnName: String, columnValue: String): List<Player> =
        playerDao.find(columnName, columnValue)

    fun findTeamsByTeamID(columnName: String, columnValue: String): List<Player> =
        playerDao.find(columnName, columnValue)

    fun delete(id: String): Boolean = playerDao.delete(id)

    fun upDataPlayer(id: String, entity: Player): Boolean = playerDao.save(id, entity)

}