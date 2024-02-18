package com.cvs.worldquiz.controller.main.discover

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.facebook.drawee.view.SimpleDraweeView

import com.cvs.worldquiz.R
import com.cvs.worldquiz.assets.FlagLoader
import com.cvs.worldquiz.db.Database
import com.cvs.worldquiz.db.model.Country
import io.realm.RealmResults
import org.jetbrains.anko.AnkoContext

/**
 * @author IgorSteblii on 09.01.17.
 */

class DiscoverAdapter(context: Context) : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    private var mStartItems: RealmResults<Country>? = null
    private var mItems: RealmResults<Country>? = null
    private val mFlagLoader: FlagLoader = FlagLoader(context)

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DiscoverItemView().createView(AnkoContext.create(parent.context, parent))
        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return mItems?.get(position)?.countryId?.toLong() ?: 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    fun setDate(items: RealmResults<Country>) {
        mItems = items
        mStartItems = items
    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    fun filter(it: CharSequence?) {
        mItems = if (it?.isEmpty() == true) {
            mStartItems
        } else {
            Database.shared
                    .where(Country::class, it.toString(), arrayOf("label", "capital", "code"))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mFlag: SimpleDraweeView? = itemView.findViewById(R.id.discover_country_image)
        private val mName: TextView? = itemView.findViewById(R.id.discover_country_name)
        private val mCode: TextView? = itemView.findViewById(R.id.discover_country_code)
        private val mContinent: TextView? = itemView.findViewById(R.id.discover_continent_value)
        private val mCapital: TextView? = itemView.findViewById(R.id.discover_capital_value)
        private val mPopulation: TextView? = itemView.findViewById(R.id.discover_population_value)
        private val mArea: TextView? = itemView.findViewById(R.id.discover_area_value)

        fun bindView(position: Int) {
            mItems?.let {
                val item = it[position]
                mName?.text = item?.label
                mCode?.text = item?.code
                mContinent?.text = item?.continent
                mCapital?.text = item?.capital
                mPopulation?.text = item?.population
                mArea?.text = item?.area
                item?.code?.let {
                    mFlagLoader.setFlag(item.code, mFlag!!)
                }
            }
        }
    }

}
