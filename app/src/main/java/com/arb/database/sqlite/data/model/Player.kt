package com.arb.database.sqlite.data.model

data class Player(val id: Long, val name: String, val team_id: Long) {
    constructor(name: String, team_id: Long) : this(0, name, team_id)
}