package com.arb.database.sqlite.data.repository

import com.arb.database.sqlite.data.local.db.dao.TeamDao
import com.arb.database.sqlite.data.model.Team

class TeamRepository(val teamDao: TeamDao) {

    fun savaTeam(team: Team): Boolean = teamDao.save(team)

    fun findTeam(): List<Team> = teamDao.findAll()

    fun findTeamById(columnName: String, columneValue: String): Team =
        teamDao.find(columnName, columneValue)[0]

    fun findTeamsByName(columnName: String, columneValue: String): List<Team> =
        teamDao.find(columnName, columneValue)

    fun findTeamsByGround(columnName: String, columneValue: String): List<Team> =
        teamDao.find(columnName, columneValue)

    fun delete(id: String): Boolean = teamDao.delete(id)

    fun upDataTeam(id: String, entity: Team): Boolean = teamDao.save(id, entity)
}