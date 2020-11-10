package com.example.gyaanop

import android.net.Uri
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GyaanAdapter(private val listener: GyaanClick): RecyclerView.Adapter<GyanViewHolder>(){

    private val items: ArrayList<Gyaan> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GyanViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gyaan_container, parent, false)
        val viewHolder = GyanViewHolder(view)
        view.setOnClickListener {
            listener.onGyaanClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: GyanViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val current = items[position]
        holder.titleView.text = current.title
        holder.author.text = current.author
        Glide.with(holder.itemView.context).load(current.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return items.size
    }

    fun updateGyaan(updatedGyaan: ArrayList<Gyaan>){
        items.clear()
        items.addAll(updatedGyaan)

        notifyDataSetChanged()
    }
}

class GyanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface GyaanClick{
    fun onGyaanClick(item: Gyaan){

    }
}