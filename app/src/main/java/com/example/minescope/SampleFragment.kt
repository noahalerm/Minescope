package com.example.minescope

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import com.google.android.material.slider.Slider
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator


class SampleFragment : Fragment(R.layout.fragment_sample) {
    lateinit var image: ImageView
    lateinit var image2: ImageView
    lateinit var slider: Slider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.imageView)
        image2 = view.findViewById(R.id.imageView2)
        slider = view.findViewById(R.id.slider)

        var con1 = 4
        var con2 = 4
        var con3 = 5

        image2.setOnClickListener {
            Picasso.get()
                .load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_3${con3}${con2}${con1}.jpg").noFade().placeholder(image.drawable).into(image)
            
            if (con1 == 9) {
                con1 = 0
                if (con2 == 9) {
                    con2 = 0
                    con3++
                } else {
                    con2++
                }
            } else {
                con1++
            }
            println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_3${con3}${con2}${con1}.jpg")
        }

        slider.addOnChangeListener { slider, value, fromUser ->
            Picasso.get()
                .load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_3${con3}${con2}${con1}.jpg").noFade().placeholder(image.drawable).into(image)

            if (con1 == 9) {
                con1 = 0
                if (con2 == 9) {
                    con2 = 0
                    con3++
                } else {
                    con2++
                }
            } else {
                con1++
            }
            println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_3${con3}${con2}${con1}.jpg")
        }
    }
}