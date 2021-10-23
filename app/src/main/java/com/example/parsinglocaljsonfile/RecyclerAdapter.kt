package com.example.parsinglocaljsonfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapter (val activity: MainActivity,val imagesList:ArrayList<images>):RecyclerView.Adapter<RecyclerAdapter.itemViewHolder>() {
    class itemViewHolder (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false
        ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val imgTitle=imagesList[position].title
        val imgURL=imagesList[position].url
        holder.itemView.apply {
            tvTitle.text=imgTitle
            Glide.with(activity).load(imgURL).into(imageView)
        }
    }

    override fun getItemCount(): Int= imagesList.size

}