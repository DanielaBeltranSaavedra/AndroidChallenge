package com.raywenderlich.android.bottomsup.ui.feed

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.ui.feed.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.custom_notification_layour.*

class SwipeToDelete (var adapter: FavoriteAdapter):ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
         var pos= viewHolder?.adapterPosition

        if (pos != null) {

            adapter.fav.removeAt(pos)
            adapter.lastfav.removeAt(pos)
            adapter.num=  adapter.fav.size


        }


    }


}