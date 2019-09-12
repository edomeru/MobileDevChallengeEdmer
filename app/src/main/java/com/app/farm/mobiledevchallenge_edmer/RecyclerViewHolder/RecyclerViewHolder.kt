package com.app.farm.mobiledevchallenge_edmer.RecyclerViewHolder

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.farm.mobiledevchallenge_edmer.DataModel.Quote
import kotlinx.android.synthetic.main.list_item_chat2.view.*

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //Listener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)

    }

    fun setData(hobby: Quote?, pos: Int, activity: AppCompatActivity) {

        hobby?.let {

            itemView.currencyCountry.text = hobby.country.replace("USD", "")
            itemView.currencyValue.text = hobby.value.toString()

        }?: run {

        }
    }
}