package com.bryonnabaines.dogbreedlist.views

/**
 * Created by bryonnabaines on 10/15/18.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import com.squareup.picasso.Picasso
import android.util.DisplayMetrics
import com.bryonnabaines.dogbreedlist.R
import kotlinx.android.synthetic.main.dog_image.view.*

// Adapter required for RecyclerView with proprietary view holder
class DogBreedListAdapter constructor(private var context: Context,
                                      private var dogList: ArrayList<String> = arrayListOf(),
                                      var clickListener: View.OnClickListener?)
    : RecyclerView.Adapter<DogBreedListAdapter.DogViewHolder>() {


    override fun onBindViewHolder(holder: DogViewHolder?, position: Int) {
        val dog = dogList[position]

        // setting parameters for the GridLayout
        val display = DisplayMetrics()
        val wm : WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(display)

        // loading the image URL with Picasso and resizing it for the layout
        if(dog.isNotEmpty()){
            Picasso.with(context)
                    .load(dog)
                    .resize(display.widthPixels / 2, 300)
                    .centerCrop()
                    .into((holder?.photoItem))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DogViewHolder {
        //This will create a new photoView holder class with the layout
        //inflated into a view. Notice the parent: is nullable-this is a
        //safe way to access variables that could be null
        return DogViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.dog_image, parent, false))
    }

    override fun getItemCount(): Int { return dogList.size }

    // A function to return a photo selected by the user
    fun getDog(adapterPosition: Int) : String { return dogList[adapterPosition] }

    inner class DogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var photoItem = itemView.photo_item

        init {
            if(clickListener != null){
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
        }
    }
}