package com.example.gyaanop

//import android.R
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
//import com.gpfreetech.neumorphism.Neumorphism
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), GyaanClick {

    private lateinit var mAdapter: GyaanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = GyaanAdapter(this)

        recyclerView.adapter = mAdapter
    }

    private fun fetchData(){
        val url = "https://inshortsapi.vercel.app/news?category=sports"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val newJsonArray = it.getJSONArray("data")
                val gyaanArray = ArrayList<Gyaan>()
                for (i in 0 until newJsonArray.length()) {
                    val gyaanJsonObject = newJsonArray.getJSONObject(i)
                    val gyaan = Gyaan(
                        gyaanJsonObject.getString("title"),
                        gyaanJsonObject.getString("author"),
                        gyaanJsonObject.getString("url"),
                        gyaanJsonObject.getString("imageUrl")
                    )
                    gyaanArray.add(gyaan)
//                    println(gyaan)
                }
                mAdapter.updateGyaan(gyaanArray)
            },
            Response.ErrorListener {
            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    override fun onGyaanClick(item: Gyaan) {
//        Toast.makeText(this,"Clicked Item is $item",Toast.LENGTH_SHORT).show()
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}
