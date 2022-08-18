package com.example.timnasscore

data class APIDataItem(
    val CurrentMinute: Int,
    val DateTimeMatch: String,
    val EventName: String,
    val IDMatch: Int,
    val NameAway: String,
    val NameHome: String,
    val PossesionAway: Int?,
    val PossesionHome: Int?,
    val RedCardAway: Int?,
    val RedCardHome: Int?,
    val ScoreAway: Int,
    val ScoreHome: Int,
    val ShotsAway: Int?,
    val ShotsHome: Int?,
    val ShotsOnTargetAway: Int?,
    val ShotsOnTargetHome: Int?,
    val StatusMatch: String,
    val YellowCardAway: Int?,
    val YellowCardHome: Int?,
    val createdAt: String,
    val updatedAt: String
)