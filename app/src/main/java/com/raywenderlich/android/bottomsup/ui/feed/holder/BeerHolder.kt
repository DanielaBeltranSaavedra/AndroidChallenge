/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.bottomsup.ui.feed.holder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.ui.feed.adapter.BeersAdapter
import kotlinx.android.synthetic.main.item_beer.view.*


class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  @SuppressLint("ResourceType")
  fun showBeer(beer: Beer, action: BeersAdapter.OnBeerItemClickListner,beersLastFav:ArrayList<Int>): Unit = with(itemView) {
    beerStyle.text = beer.style.name
    beerName.text = beer.name
    val mediumImage = beer.labels.medium
    val largeImage = beer.labels.large

    Glide.with(itemView).load(if (largeImage.isNotBlank()) {
      largeImage
    } else {
      mediumImage
    }).into(beerImage)

    var button_background : Int = 1;

     if(beersLastFav.contains(adapterPosition)){
       itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp)
     }
    itemView.favBtn.setOnClickListener({
      action.onItemClick(beer, adapterPosition)
      if(button_background==1){
      itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp)
        button_background=2
      }else if(button_background==2){
        itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp)
        button_background=1
      }

      })


      itemView.setOnClickListener({
        action.onItemClick(beer, adapterPosition)
        if(button_background==1){
          itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp)
          button_background=2
        }else if(button_background==2){
          itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp)
          button_background=1
        }
      })
    }


}


