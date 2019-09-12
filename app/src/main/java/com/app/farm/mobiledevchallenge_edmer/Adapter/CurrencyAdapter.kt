package com.app.farm.mobiledevchallenge_edmer.Adapter
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.farm.mobiledevchallenge_edmer.DataModel.LiveCurrency
import com.app.farm.mobiledevchallenge_edmer.R
import com.app.farm.mobiledevchallenge_edmer.RecyclerViewHolder.RecyclerViewHolder


class CurrencyAdapter (private val activity: AppCompatActivity, private val itemClickListener: RecyclerViewHolder.ItemClickListener?, private var currencyAdapter: LiveCurrency?) : RecyclerView.Adapter<RecyclerViewHolder>() {

	private var mRecyclerView : RecyclerView? = null

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		if (recyclerView != null) {
			super.onAttachedToRecyclerView(recyclerView)
		}
		mRecyclerView = recyclerView
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		if (recyclerView != null) {
			super.onDetachedFromRecyclerView(recyclerView)
		}
		mRecyclerView = null

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
		val view = LayoutInflater.from(this.activity).inflate(R.layout.list_item_chat2, parent, false)
		view.setOnClickListener { view ->
			mRecyclerView?.let {

					itemClickListener?.onItemClick(view, it.getChildAdapterPosition(view))

			}
		}

		return RecyclerViewHolder(view)
	}

	override fun getItemCount(): Int {
		var quotes = currencyAdapter!!.quotes.count()
		return quotes


	}

	override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
		val hobby = currencyAdapter!!.quotes.get(position)
		holder.setData(hobby, position,activity)
	}






}
