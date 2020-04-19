package com.raywenderlich.android.bottomsup.ui.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.ui.feed.holder.FavoriteHolder


class FavoriteAdapter (var fav: ArrayList<Beer>): RecyclerView.Adapter<FavoriteHolder>() {
    interface OnFavItemClickListner{
        fun onItemClick(item: Beer, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_beer, parent, false)

        return FavoriteHolder(view)
    }

     override fun onBindViewHolder(holder: FavoriteHolder?, position: Int) {
        val beer = fav[position]

        holder?.run { showFav(beer) }
    }

    fun clearIfNeeded(page: Int) {
        if (page == 1) {
            fav.clear()
        }
    }

    fun addItems(newBeers: List<Beer>) {
        fav.addAll(newBeers.filter { beer ->
            beer.labels.medium.isNotBlank() || beer.labels.large.isNotBlank()
        })
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        fav.removeAt(position)
        notifyItemRemoved(position)

    }
    override fun getItemCount() = fav.size



}
