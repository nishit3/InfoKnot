package com.example.wholeprojectfiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class workshopAdapter(var context: Context,data:ArrayList<workshop>):RecyclerView.Adapter<workshopAdapter.viewholder>()
{
    var data:ArrayList<workshop>
    init
    {
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var layout: View
        layout= LayoutInflater.from(context).inflate(R.layout.workshopdesign,parent,false)
        return viewholder(layout)
    }


    class viewholder(itemview: View):RecyclerView.ViewHolder(itemview){
        var headline: TextView
        var dateformat: TextView
        var sponser: TextView
        var foi: TextView
        init {
            headline=itemview.findViewById(R.id.wheading)
            dateformat=itemview.findViewById(R.id.wdatformat)
            sponser=itemview.findViewById(R.id.wsponsers)
            foi=itemview.findViewById(R.id.wfoi)
        }

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var dataary = data[position]
        holder.sponser.text=dataary.wsponser
        holder.headline.text=dataary.wheading
        holder.dateformat.text=dataary.wdate
        holder.foi.text=dataary.wfoi

    }

    override fun getItemCount(): Int {
        return data.size
    }

}