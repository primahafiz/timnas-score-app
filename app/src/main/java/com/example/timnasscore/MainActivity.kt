package com.example.timnasscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvMatches: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var listMatch: ArrayList<FootballMatch>
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMatches = findViewById(R.id.rv_matches)
        progressBar = findViewById(R.id.progressBar)
        rvMatches.setHasFixedSize(true)

        listMatch = ArrayList()

        getAPIData()
    }

    private fun showRecyclerList(): Unit {
        rvMatches.layoutManager = LinearLayoutManager(this)
        val listMatchAdapter = ListMatchAdapter(this, listMatch)
        rvMatches.adapter = listMatchAdapter

        listMatchAdapter.setOnItemClickCallback(object : ListMatchAdapter.OnItemCallback {
            override fun onItemClicked(data: FootballMatch) {
                val detailIntent = Intent(this@MainActivity, DetailMatchActivity::class.java)

                detailIntent.putExtra(DetailMatchActivity.MATCH_DATA, data)

                startActivity(detailIntent)
            }
        })
    }

    private fun getAPIData(){


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api-timnasscore.herokuapp.com/")
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getMatchData()

        retrofitData.enqueue(object : Callback<List<APIDataItem>?> {
            override fun onResponse(call: Call<List<APIDataItem>?>, response: Response<List<APIDataItem>?>) {
                val responseBody = response.body()!!

                for(matchData in responseBody){

                    val matchGoal = ArrayList<Goal>()

                    val retrofitBuilderGoal = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api-timnasscore.herokuapp.com/")
                        .build()
                        .create(APIInterface::class.java)

                    val retrofitDataGoal = retrofitBuilderGoal.getGoalData("https://api-timnasscore.herokuapp.com/goal?IDMatch=${matchData.IDMatch}")

                    retrofitDataGoal.enqueue(object : Callback<List<APIDataGoalItem>?> {
                        override fun onResponse(callGoal: Call<List<APIDataGoalItem>?>, responseGoal: Response<List<APIDataGoalItem>?>) {
                            val responseGoalBody = responseGoal.body()!!
                            for(goalMatch in responseGoalBody){
                                matchGoal.add(Goal(goalMatch.PlayerName,goalMatch.TeamName,goalMatch.Minute))
                            }
                            listMatch.add(FootballMatch(matchData.IDMatch,matchData.DateTimeMatch,matchData.NameHome,matchData.NameAway,matchData.ScoreHome,matchData.ScoreAway,matchData.ShotsHome,matchData.ShotsAway,matchData.ShotsOnTargetHome,matchData.ShotsOnTargetAway,matchData.PossesionHome,matchData.PossesionAway,matchData.YellowCardHome,matchData.YellowCardAway,matchData.RedCardHome,matchData.RedCardAway,matchData.EventName,matchData.CurrentMinute,matchData.StatusMatch,
                                GoalsInMatch(matchGoal)
                            ))
                            if(listMatch.size==10){
                                listMatch.sortByDescending { it.dateMatch }
                                progressBar.setVisibility(View.GONE)
                                showRecyclerList()
                            }
                        }

                        override fun onFailure(callGoal: Call<List<APIDataGoalItem>?>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })

                }
            }


            override fun onFailure(call: Call<List<APIDataItem>?>, t: Throwable) {

            }
        })
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        setMode(item.itemId)
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun setMode(selectedMode: Int) {
//        when (selectedMode) {
//            R.id.profile_menu -> {
//                val profileIntent = Intent(this@MainActivity, AboutActivity::class.java)
//                startActivity(profileIntent)
//            }
//        }
//    }


}