package com.example.timnasscore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.StringBuilder

class DetailMatchActivity : AppCompatActivity() {

    private lateinit var tvDetailEventNameAndDate : TextView
    private lateinit var imgHome : ImageView
    private lateinit var imgAway : ImageView
    private lateinit var tvScoreHome : TextView
    private lateinit var tvScoreAway : TextView
    private lateinit var tvHome : TextView
    private lateinit var tvAway : TextView
    private lateinit var imgHomeStats : ImageView
    private lateinit var imgAwayStats : ImageView
    private lateinit var tvShotsHome : TextView
    private lateinit var tvShotsAway : TextView
    private lateinit var tvShotsOnTargetHome : TextView
    private lateinit var tvShotsOnTargetAway : TextView
    private lateinit var tvPossesionHome : TextView
    private lateinit var tvPossesionAway : TextView
    private lateinit var tvYellowCardHome : TextView
    private lateinit var tvYellowCardAway : TextView
    private lateinit var tvRedCardHome : TextView
    private lateinit var tvRedCardAway : TextView
    private lateinit var tvGoalHome : TextView
    private lateinit var tvGoalAway : TextView

    companion object{
        const val MATCH_DATA = "match_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_row_match)

        tvDetailEventNameAndDate = findViewById(R.id.tv_detail_event_name_and_date)
        imgHome = findViewById(R.id.img_detail_home)
        imgAway = findViewById(R.id.img_detail_away)
        tvScoreHome = findViewById(R.id.tv_detail_score_home)
        tvScoreAway = findViewById(R.id.tv_detail_score_away)
        tvHome = findViewById(R.id.tv_detail_home)
        tvAway = findViewById(R.id.tv_detail_away)
        imgHomeStats = findViewById(R.id.img_detail_home_stats)
        imgAwayStats = findViewById(R.id.img_detail_away_stats)
        tvShotsHome = findViewById(R.id.tv_shot_home)
        tvShotsAway = findViewById(R.id.tv_shot_away)
        tvShotsOnTargetHome = findViewById(R.id.tv_shot_on_target_home)
        tvShotsOnTargetAway = findViewById(R.id.tv_shot_on_target_away)
        tvPossesionHome = findViewById(R.id.tv_possesion_home)
        tvPossesionAway = findViewById(R.id.tv_possesion_away)
        tvYellowCardHome = findViewById(R.id.tv_yellow_card_home)
        tvYellowCardAway = findViewById(R.id.tv_yellow_card_away)
        tvRedCardHome = findViewById(R.id.tv_red_card_home)
        tvRedCardAway = findViewById(R.id.tv_red_card_away)
        tvGoalHome = findViewById(R.id.tv_goal_home)
        tvGoalAway = findViewById(R.id.tv_goal_away)


        val matchDetail : FootballMatch = intent.extras?.get(MATCH_DATA) as FootballMatch

        tvDetailEventNameAndDate.text = "${matchDetail.eventName} - ${matchDetail.dateMatch}"
        imgHome.setImageResource(getImageIdHome(this,matchDetail))
        imgAway.setImageResource(getImageIdAway(this,matchDetail))

        if(matchDetail.statusMatch=="HT" || matchDetail.statusMatch=="ET" || matchDetail.statusMatch=="P" || matchDetail.statusMatch=="FT" || matchDetail.statusMatch=="AET" || matchDetail.statusMatch=="PEN"){
            tvScoreHome.text = matchDetail.scoreHome.toString()
            tvScoreAway.text = matchDetail.scoreAway.toString()
        }else if(matchDetail.statusMatch=="1H" || matchDetail.statusMatch=="2H"){
            tvScoreHome.text = matchDetail.scoreHome.toString()
            tvScoreAway.text = matchDetail.scoreAway.toString()
        }else {
            tvScoreHome.text = ""
            tvScoreAway.text = ""
        }

        tvHome.text = matchDetail.nameHome
        tvAway.text = matchDetail.nameAway
        imgHomeStats.setImageResource(getImageIdHome(this,matchDetail))
        imgAwayStats.setImageResource(getImageIdAway(this,matchDetail))
        tvShotsHome.text = matchDetail.shotsHome.toString()
        tvShotsAway.text = matchDetail.shotsAway.toString()
        tvShotsOnTargetHome.text = matchDetail.shotsOnTargetHome.toString()
        tvShotsOnTargetAway.text = matchDetail.shotsOnTargetAway.toString()
        tvPossesionHome.text = "${matchDetail.possesionHome}%"
        tvPossesionAway.text = "${matchDetail.possesionAway}%"
        tvYellowCardHome.text = matchDetail.yellowCardHome.toString()
        tvYellowCardAway.text = matchDetail.yellowCardAway.toString()
        tvRedCardHome.text = matchDetail.redCardHome.toString()
        tvRedCardAway.text = matchDetail.redCardAway.toString()
        tvGoalHome.text = matchDetail.goals.getMappedGoal(matchDetail.nameHome)
        tvGoalAway.text = matchDetail.goals.getMappedGoal(matchDetail.nameAway)



    }

    fun formatName(teamName : String) : String{
        var name = StringBuilder()
        name.append(teamName)
        for(i in name.indices){
            if(name[i] == '-'){
                name[i] = '_'
            }else{
                name[i] = name[i].lowercaseChar()
            }
        }
        return name.toString()
    }

    fun getImageIdHome(context : Context, matchDetail : FootballMatch) : Int{
        return context.resources.getIdentifier(formatName(matchDetail.nameHome),"drawable",context.packageName)
    }
    fun getImageIdAway(context : Context, matchDetail : FootballMatch) : Int{
        return context.resources.getIdentifier(formatName(matchDetail.nameAway),"drawable",context.packageName)
    }
}