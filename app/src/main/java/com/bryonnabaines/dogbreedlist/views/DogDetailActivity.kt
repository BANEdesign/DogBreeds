package com.bryonnabaines.dogbreedlist.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bryonnabaines.dogbreedlist.R
import com.squareup.picasso.Picasso

class DogDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        val imageView : ImageView = findViewById(R.id.imageView)
        val photo = intent.getSerializableExtra(DOG) as String

        //Picasso will load in into the imageView
        photo.let {
            Picasso.with(this).load(it)
                    .into(imageView)
            supportActionBar?.title = parseBreedFromUrl(it)
        }
        imageView.setOnClickListener{
            finish()
        }
    }

    private fun parseBreedFromUrl( url: String) : String {

        val shortUrl = url.removeRange(0,30)
        Log.d("DOG DETAIL", shortUrl)

        return shortUrl.removeRange(shortUrl.indexOf('/'), shortUrl.length)
    }

    companion object {
        val DOG = "DOG"
    }
}
