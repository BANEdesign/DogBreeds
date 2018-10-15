package com.bryonnabaines.dogbreedlist.viewModels

/**
 * Created by bryonnabaines on 10/15/18.
 */

import android.util.Log
import com.bryonnabaines.dogbreedlist.api.Api
import com.bryonnabaines.dogbreedlist.models.DogList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogBreedListViewModel {

    private val api: Api
    // Sets up retrofit for api request and adds Google's json converter to parse the response
    init{
        val retrofit = Retrofit.Builder().
                baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(Api::class.java)
    }
    // This function makes a call to the api to request the dog list
    fun fetchDogList(callback : Callback<DogList>){
        val call = api.getDogBreeds()
        call.enqueue(callback)
        Log.d("VIEW MODEL","requesting photos from api ")
    }
}