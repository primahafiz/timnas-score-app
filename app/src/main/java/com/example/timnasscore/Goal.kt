package com.example.timnasscore

import java.io.Serializable

data class Goal(val playerName : String, val teamName : String, val minute : Int) : Serializable