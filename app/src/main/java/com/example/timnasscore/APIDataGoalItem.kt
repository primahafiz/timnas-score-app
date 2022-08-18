package com.example.timnasscore

data class APIDataGoalItem(
    val ExtraMinute: Int?,
    val IDGoal: Int,
    val IDMatch: Int,
    val Minute: Int,
    val PlayerName: String,
    val TeamName: String,
    val createdAt: String,
    val updatedAt: String
)