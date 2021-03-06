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

package com.raywenderlich.android.bottomsup.ui.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.model.Beer
import com.raywenderlich.android.bottomsup.ui.feed.holder.BeerHolder



class BeersAdapter(var fav: ArrayList<Int>, var clickListner: OnBeerItemClickListner): RecyclerView.Adapter<BeerHolder>() {
  interface OnBeerItemClickListner{
    fun onItemClick(item:Beer,position: Int)

  }


  private val beers = mutableListOf<Beer>()



  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerHolder {
    val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_beer, parent, false)

    return BeerHolder(view)
  }

  override fun onBindViewHolder(holder: BeerHolder?, position: Int) {
    val beer = beers[position]

    holder?.run { showBeer(beer,clickListner,fav) }
  }

  fun clearIfNeeded(page: Int) {
    if (page == 1) {
      beers.clear()
    }
  }

  fun addItems(newBeers: List<Beer>) {
    beers.addAll(newBeers.filter { beer ->
      beer.labels.medium.isNotBlank() || beer.labels.large.isNotBlank()
    })
    notifyDataSetChanged()
  }

  override fun getItemCount() = beers.size

  fun getItems(): List<Beer> {
    return beers
  }


}
