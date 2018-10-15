package com.bryonnabaines.dogbreedlist.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.bryonnabaines.dogbreedlist.R
import com.bryonnabaines.dogbreedlist.models.DogList
import com.bryonnabaines.dogbreedlist.viewModels.DogBreedListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var dogs : ArrayList<String>? = arrayListOf()
    lateinit var recyclerView: RecyclerView
    private var dogBreedListAdapter : DogBreedListAdapter = DogBreedListAdapter(this, this.dogs!!, this)
    var viewModel: DogBreedListViewModel = DogBreedListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //sets the activity background color without modifying the app theme
        this.window.decorView.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

        setupAdapter()
        getDogList()


    }
    // This send the data to the adapter and handles errors fetching the request
    private fun getDogList(){

        //This creates a callback object that is send to the retriever. It has methods to respond
        //if the callback fails or succeeds
        val callback = object : Callback<DogList> {
            //TODO I think this would be better as an observer
            override fun onFailure(call: Call<DogList>?, t: Throwable?) {
                Log.e("MainActivity ", "Problems calling API", t)
            }
            // This function handles the api response as a list of dog image URLs and send them to the
            // recyclerView adapter
            override fun onResponse(call: Call<DogList>?, response: Response<DogList>?) {
                Log.d("MAIN ACTIVITY", "Successful response")

                if (response!!.isSuccessful) response.let {
                    Log.d("parsing response", response.toString())
                    dogs = it?.body()?.message
                    dogBreedListAdapter = DogBreedListAdapter(applicationContext, dogs!!, this@MainActivity)
                    recyclerView.adapter = dogBreedListAdapter

                }
            }
        }
        viewModel.fetchDogList(callback)
        Log.d("Main Activity", "Photos being requested")
    }

    private fun setupAdapter(){
        recyclerView = findViewById(R.id.dogBreedList)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun onClick(view: View?) {

        val intent = Intent(this, DogDetailActivity::class.java)
        val holder = view?.tag as DogBreedListAdapter.DogViewHolder
        // Starts an intent to inflate the Detail View of the image selected
        intent.putExtra(DogDetailActivity.DOG,
                dogBreedListAdapter.getDog(holder.adapterPosition))
        startActivity(intent) //creates intent to start the photo activity
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

