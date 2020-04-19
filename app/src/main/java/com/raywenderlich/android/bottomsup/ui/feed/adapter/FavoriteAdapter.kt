package com.raywenderlich.android.bottomsup.ui.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.ui.feed.holder.FavoriteHolder


class FavoriteAdapter (var fav: ArrayList<Beer>,var lastfav: ArrayList<Int>, var num : Int): RecyclerView.Adapter<FavoriteHolder>() {
    interface OnFavItemClickListner{
        fun onItemClick(item: Beer, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_beer, parent, false)

        return FavoriteHolder(view)
    }
    var position= 0
     override fun onBindViewHolder(holder: FavoriteHolder?, pos: Int) {
        val beer = fav[pos]
         position=pos
        holder?.run { showFav(beer) }
    }


    fun getPos(): Int {
        return position
    }
    fun getItem():Beer{
        val beer = fav[position]
        return  beer
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
        lastfav.remove(position)
        notifyItemRemoved(position)


    }
    override fun getItemCount() = fav.size




}
