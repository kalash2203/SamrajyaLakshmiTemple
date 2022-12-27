package com.example.samrajyalakshmitemple.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import okio.IOException


/**
 * A custom object to create a common functions for Glide which can be used in whole application.
 */
class GlideLoader(val context: Context) {

    /**
     * A function to load image from Uri or URL for the user profile picture.
     */

    fun loadUserPicture(image: Any?, imageView: ImageView?) {
        try {
            // Load the user image in the ImageView.
            if (imageView != null) {
                Glide
                    .with(context)
                    .load(image) // Uri or URL of the image.circleCrop() // Scale type of the image.
                    .into(imageView)
            } // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }




}