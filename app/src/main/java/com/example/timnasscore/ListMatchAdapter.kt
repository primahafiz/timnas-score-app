package com.example.timnasscore

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.lang.StringBuilder

class ListMatchAdapter(val context : Context, val listMatch : ArrayList<FootballMatch>) : RecyclerView.Adapter<ListMatchAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback : OnItemCallback

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var matchBox : LinearLayout = itemView.findViewById(R.id.match_box)
        var imgHome : ImageView = itemView.findViewById(R.id.img_home)
        var imgAway : ImageView = itemView.findViewById(R.id.img_away)
        var tvHome : TextView = itemView.findViewById(R.id.tv_name_home)
        var tvAway : TextView = itemView.findViewById(R.id.tv_name_away)
        var tvScoreHome : TextView = itemView.findViewById(R.id.tv_score_home)
        var tvScoreAway : TextView = itemView.findViewById(R.id.tv_score_away)
        var tvDate : TextView = itemView.findViewById(R.id.tv_date_match)
        var tvStatusMatch : TextView = itemView.findViewById(R.id.tv_match_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_match,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val match = listMatch[position]

        holder.imgHome.setImageResource(getImageIdHome(context,position))
        holder.imgAway.setImageResource(getImageIdAway(context,position))

        holder.tvHome.text = match.nameHome
        holder.tvAway.text = match.nameAway

        if(match.statusMatch=="HT" || match.statusMatch=="ET" || match.statusMatch=="P" || match.statusMatch=="FT" || match.statusMatch=="AET" || match.statusMatch=="PEN"){
            holder.tvScoreHome.text = match.scoreHome.toString()
            holder.tvScoreAway.text = match.scoreAway.toString()
            holder.tvStatusMatch.text = match.statusMatch
        }else if(match.statusMatch=="1H" || match.statusMatch=="2H"){
            holder.tvScoreHome.text = match.scoreHome.toString()
            holder.tvScoreAway.text = match.scoreAway.toString()
            holder.tvStatusMatch.text = "${match.currentMinute.toString()}'"
            holder.tvStatusMatch.setTextColor(Color.parseColor("#1e8e3e"))
        }else{
            holder.tvScoreHome.text = ""
            holder.tvScoreAway.text = ""
            holder.tvStatusMatch.text = match.statusMatch
        }

        holder.tvDate.text = match.dateMatch

        holder.matchBox.setOnClickListener{onItemClickCallback.onItemClicked(match)}
    }

    override fun getItemCount(): Int {
        return listMatch.size
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

    fun getImageIdHome(context : Context,position : Int) : Int{
        return context.resources.getIdentifier(formatName(listMatch[position].nameHome),"drawable",context.packageName)
    }
    fun getImageIdAway(context : Context,position : Int) : Int{
        return context.resources.getIdentifier(formatName(listMatch[position].nameAway),"drawable",context.packageName)
    }

    interface OnItemCallback{
        fun onItemClicked(data : FootballMatch)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}