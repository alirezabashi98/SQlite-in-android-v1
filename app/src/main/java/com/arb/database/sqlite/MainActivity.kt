package com.arb.database.sqlite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.arb.database.sqlite.data.local.db.AppDatabase
import com.arb.database.sqlite.data.local.db.dao.TeamDao
import com.arb.database.sqlite.data.model.Team
import com.arb.database.sqlite.data.repository.TeamRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase(this)

        val teamDao = TeamDao(appDatabase)
        //val playeDao = PlayerDao(appDatabase)

        val teamRepository = TeamRepository(teamDao)
        //val playerRepository = PlayerRepository(playeDao)

//        for (item in 1..3) {
//            val result = teamRepository.savaTeam(Team("name$item", "ground$item"))
//        }
//        teamRepository.findTeam()
//            .forEach { Log.i("TEST", "${it.id}  |  ${it.name}  |  ${it.ground}") }

//        val delete = teamRepository.delete("4")
        //Toast.makeText(this,"$delete",Toast.LENGTH_LONG).show()

//        Log.i("TEST","${teamRepository.findTeamsByGround(AppDatabase.TEAM_GROUND,"ground2")}")
 
    }
}