package com.example.timnasscore

import java.io.Serializable

class FootballMatch(val IDMatch: Int, val dateMatch: String, val nameHome: String, val nameAway: String, val scoreHome: Int, val scoreAway: Int, val shotsHome: Int?, val shotsAway: Int?, val shotsOnTargetHome: Int?, val shotsOnTargetAway: Int?, val possesionHome: Int?, val possesionAway: Int?, val yellowCardHome: Int?, val yellowCardAway: Int?, val redCardHome: Int?, val redCardAway: Int?, val eventName: String, val currentMinute: Int?, val statusMatch: String,val goals:GoalsInMatch) : Serializable{

}