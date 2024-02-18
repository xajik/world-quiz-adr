package com.cvs.worldquiz.controller.main.rules

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cvs.worldquiz.R
import org.jetbrains.anko.AnkoContext

class RulesRecyclerAdapter(val data: Array<Array<String>>) : RecyclerView.Adapter<RulesRecyclerAdapter.RulesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        return RulesViewHolder(RulesItemView()
                .createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return data[0].size
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.bind(data[0][position], data[1][position])
    }

    class RulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.rules_title)!!
        private val subTitle = itemView.findViewById<TextView>(R.id.rules_sub_title)!!

        fun bind(title: String, subtitle: String) {
            this.title.text = title
            this.subTitle.text = subtitle
        }
    }

}