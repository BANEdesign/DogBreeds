package com.bryonnabaines.dogbreedlist.api

/**
 * Created by bryonnabaines on 10/15/18.
 */

import com.bryonnabaines.dogbreedlist.models.DogList
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("breeds/image/random/50")
    fun getDogBreeds() : Call<DogList>
}