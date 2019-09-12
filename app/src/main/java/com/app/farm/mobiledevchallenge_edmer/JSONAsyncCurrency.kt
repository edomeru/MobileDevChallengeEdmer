package com.app.farm.mobiledevchallenge_edmer

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject


class  JSONAsyncCurrency(var activity: AppCompatActivity): AsyncTask<Void, Void, JSONObject?>() {

        val request = Request.Builder().url(Constants.base_url.plus(Constants.endpoint_live.plus(Constants.access_key))).build()
        val quoteObject = JSONObject()
        lateinit var progressDialog: ProgressDialog


             override fun onPreExecute() {
                 progressDialog = ProgressDialog(activity)
                 progressDialog.setMessage("Downloading")
                 progressDialog.setCancelable(false)
                 progressDialog.show()
             }
            override fun doInBackground(vararg params: Void?): JSONObject? {


                var client = OkHttpClient()
                 val  response = client.newCall(request).execute()
                        val body = response?.body()?.string()
                        val json = JSONObject(body)
                        val quotes = json.getJSONObject("quotes")
                        var e = quotes.names()

                        val quoteArr = JSONArray()
                        var s = 0
                        while (s != e.length()) {
                            quoteArr.put(s,quotes.getDouble(e[s].toString()))
                            s += 1
                        }

                        val quoteArr2 = JSONArray()
                        var t = 0
                        while (t != quoteArr.length()) {
                            var quoteObjectWrap1 = JSONObject()
                            quoteObjectWrap1.put("Country",e[t])
                            quoteObjectWrap1.put("Value",quoteArr[t])
                            println(t)
                            t += 1

                            quoteArr2.put(quoteObjectWrap1)
                        }

                        quoteObject!!.put("success",json.getBoolean("success"))
                        quoteObject!!.put("timestamp",json.getInt("timestamp"))
                        quoteObject!!.put("source",json.getString("source"))
                        quoteObject!!.put("quotes",quoteArr2)

                        return quoteObject
            }

            override fun onPostExecute(result: JSONObject?) {
                super.onPostExecute(result)
                progressDialog.dismiss()

            }


    }


