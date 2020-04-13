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

package com.raywenderlich.android.bottomsup.ui.feed

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.raywenderlich.android.bottomsup.R
import com.raywenderlich.android.bottomsup.common.getViewModel
import com.raywenderlich.android.bottomsup.common.subscribe
import com.raywenderlich.android.bottomsup.ui.feed.adapter.BeersAdapter
import com.raywenderlich.android.bottomsup.viewmodel.BeersViewModel
import kotlinx.android.synthetic.main.activity_beers.*


class BeersActivity : AppCompatActivity() {
  private val viewModel by lazy { getViewModel<BeersViewModel>() }
  private val adapter = BeersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_beers)
        if(savedInstanceState!=null){
            restoreState(savedInstanceState)
        }

    val cm= baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networInfo= cm.activeNetworkInfo
    if(networInfo != null && networInfo.isConnected){

        if(networInfo.type== ConnectivityManager.TYPE_WIFI){
            Toast.makeText(baseContext, "Connected via WIFI Network", Toast.LENGTH_SHORT).show()

        }
        if(networInfo.type== ConnectivityManager.TYPE_MOBILE){
          Toast.makeText(baseContext, "Connected via MOBILE Network", Toast.LENGTH_SHORT).show()

    }


    }
      else{
        val builder= AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
        builder.setMessage("Please check your internet connection")

        builder.setNegativeButton("Cancel",{dialogInterface: DialogInterface, which: Int ->  })
        builder.show()
        Toast.makeText(baseContext, "No Internet Connection", Toast.LENGTH_SHORT).show()


    }
      initializeUi()

    viewModel.errorData.subscribe(this, this::setErrorVisibility)
    viewModel.loadingData.subscribe(this, this::showLoading)
    viewModel.pageData.subscribe(this, adapter::clearIfNeeded)
    viewModel.beerData.subscribe(this, adapter::addItems)
        viewModel.getBeers()


  }
    private fun initializeUi() {
    beersList.layoutManager = GridLayoutManager(this, 2)
    beersList.itemAnimator = DefaultItemAnimator()
    beersList.adapter = adapter

    pullToRefresh.setOnRefreshListener(viewModel::onRefresh)
  }

  private fun showLoading(isLoading: Boolean) {
    pullToRefresh.isRefreshing = isLoading
      val cm= baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val networInfo= cm.activeNetworkInfo
      if(networInfo != null && networInfo.isConnected){

          if(networInfo.type== ConnectivityManager.TYPE_WIFI){
              Toast.makeText(baseContext, "Connected via WIFI Network", Toast.LENGTH_SHORT).show()

          }
          if(networInfo.type== ConnectivityManager.TYPE_MOBILE){
              Toast.makeText(baseContext, "Connected via MOBILE Network", Toast.LENGTH_SHORT).show()

          }


      }
      else{
          val builder= AlertDialog.Builder(this)
          builder.setTitle("No Internet Connection")
          builder.setMessage("Please check your internet connection")

          builder.setNegativeButton("Cancel",{dialogInterface: DialogInterface, which: Int ->  })
          builder.show()
          Toast.makeText(baseContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
      }
  }

  private fun setErrorVisibility(shouldShow: Boolean) {
    errorView.visibility = if (shouldShow) View.VISIBLE else View.GONE
    beersList.visibility = if (!shouldShow) View.VISIBLE else View.GONE
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     super.onCreateOptionsMenu(menu)
    menuInflater.inflate(R.menu.menu,menu)
    return true
  }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       var selectedOption =""
        when(item?.itemId){

            R.id.favorites-> {
                setContentView(R.layout.favorites)
                selectedOption ="Favorites"
            }
            R.id.home-> {
                
                selectedOption ="Home"
            }
        }
        Toast.makeText(this,"Option : "+ selectedOption,Toast.LENGTH_SHORT).show()
    return super.onOptionsItemSelected(item)
    }





}
