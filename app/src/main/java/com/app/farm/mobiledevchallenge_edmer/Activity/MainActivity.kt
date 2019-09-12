package com.app.farm.mobiledevchallenge_edmer.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.app.farm.mobiledevchallenge_edmer.Adapter.CurrencyAdapter
import com.app.farm.mobiledevchallenge_edmer.DataModel.LiveCurrency
import com.app.farm.mobiledevchallenge_edmer.R
import com.app.farm.mobiledevchallenge_edmer.RecyclerViewHolder.RecyclerViewHolder
import com.app.farm.mobiledevchallenge_edmer.JSONAsyncCurrency
import com.google.gson.GsonBuilder
import org.json.JSONObject


class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    private var selectedValue:Double? = null

    override fun onItemClick(view: View, position: Int) {
        Log.d("onItemClick2", "POS $position")
        selectedValue =   liveCurrency!!.quotes.get(position).value
       // Log.d("onItemClick2", "Country $we")
        buttonSelectedCurrency!!.text =  liveCurrency!!.quotes.get(position).country.replace("USD", "")

    }

    var liveCurrency: LiveCurrency? = null

    private var recyclerview: RecyclerView? = null
    private var editTextAmount: EditText? = null
    private var buttonSelectedCurrency: Button? = null
    private var buttonEnterMe: Button? = null
    private var TextviewTotal: TextView? = null
    var currencyAdapter:CurrencyAdapter? = null
    private var fetched_json :JSONObject? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initialize()

        buttonEnterMe!!.setOnClickListener{

            if (selectedValue != null && editTextAmount != null){
          var total =  (selectedValue !!.times(editTextAmount!!.text.toString().toDouble()))
                TextviewTotal!!.text =  total.toString()
            }
        }

        var jsonObject = JSONAsyncCurrency(this).execute()
        val gson = GsonBuilder().create()
        liveCurrency = gson.fromJson( jsonObject.get().toString(), LiveCurrency::class.java)

        callRecyclerView()

    }


    fun callRecyclerView(){
        currencyAdapter = CurrencyAdapter(this@MainActivity!!, this@MainActivity!!, liveCurrency!!)
        this@MainActivity!!.runOnUiThread(
            {
                recyclerview!!.adapter = currencyAdapter

            })
    }

    fun initialize(){
    recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
    editTextAmount = findViewById<EditText>(R.id.editextAmount)
    buttonSelectedCurrency =  findViewById<Button>(R.id.buttonCountry)
    buttonEnterMe = findViewById<Button>(R.id.buttonEnterMe)
    TextviewTotal = findViewById<TextView>(R.id.TextviewTotal)
    this.recyclerview!!.layoutManager = GridLayoutManager(this,5)
}

}
