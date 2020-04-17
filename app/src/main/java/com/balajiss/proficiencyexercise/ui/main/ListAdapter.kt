package com.balajiss.proficiencyexercise.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.balajiss.proficiencyexercise.R
import com.balajiss.proficiencyexercise.data.main.DataResponseItem
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception
import javax.inject.Inject

class ListAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    val data = ArrayList<DataResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(data[position])
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var title: TextView = itemView.findViewById(R.id.title)
    private var description: TextView = itemView.findViewById(R.id.description)
    private var image: ImageView = itemView.findViewById(R.id.image)

    fun updateView(dataItem: DataResponseItem) {
        title.text = dataItem.title
        description.text = dataItem.description

        if (dataItem.imageHref != null) {
            /*
            * Picasso caches the images fetched.
            * NetworkPolicy.OFFLINE - First, we try to load image from cache
            * In case of failed to load from cache, the image is downloaded from url
            * */
            Picasso.get().load(dataItem.imageHref)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.loading)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(image, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        Picasso.get().load(dataItem.imageHref)
                            .error(R.drawable.ic_broken_image)
                            .placeholder(R.drawable.loading)
                            .into(image)
                    }
                })
        } else {
            Picasso.get().load(R.drawable.ic_broken_image).into(image)
        }
    }
}