package com.example.timnasscore

import java.io.Serializable

class GoalsInMatch(val listGoal : List<Goal>) : Serializable{
    private val mp = mutableMapOf<String,MutableMap<String,MutableSet<Int>>>()
    init {
        for(i in listGoal.indices){
            if(!mp.containsKey(listGoal[i].teamName)){
                mp[listGoal[i].teamName] = mutableMapOf()
            }
            if(!mp[listGoal[i].teamName]?.containsKey(listGoal[i].playerName)!!){
                mp[listGoal[i].teamName]?.set(listGoal[i].playerName, mutableSetOf())
            }
            mp[listGoal[i].teamName]?.get(listGoal[i].playerName)?.add(listGoal[i].minute)
        }
    }

    fun getMappedGoal(teamName : String) : String{
        var res = ""
        mp[teamName]?.forEach { (k,v) ->
            val sets = mp[teamName]?.get(k)
            res+=k
            sets?.forEach {
                res+=" $it'"
            }
            res+="\n"

        }
        return res
    }

}