package com.example.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.minescope.R
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.squareup.picasso.Picasso


class SampleFragment : Fragment(R.layout.fragment_sample) {
    //ATTRIBUTES
    private lateinit var image: ImageView
    private lateinit var image2: ImageView
    private lateinit var switch: SwitchMaterial
    private lateinit var slider: Slider

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        image = view.findViewById(R.id.image)
        image2 = view.findViewById(R.id.imageView2)
        switch = view.findViewById(R.id.lpa_switch)
        slider = view.findViewById(R.id.slider)

        //URL VARIABLES
        val firstValueLPNA = 3544
        val firstValueLPA = 3363
        var lpa = "LPNA"

        //IMAGE INITIALIZATION
        Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA}.jpg")
            .noFade().placeholder(image.drawable).into(image)

        //ON CLICK
        image2.setOnClickListener {
            //IMAGE UPDATE
            if (lpa == "LPNA") {
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA+slider.value.toInt()+1}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

                //SLIDER UPDATE
                slider.value++

                //LOG
                println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA+slider.value.toInt()}.jpg")
            }
            else {
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA+slider.value.toInt()+1}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

                //SLIDER UPDATE
                slider.value++

                //LOG
                println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA+slider.value.toInt()}.jpg")
            }
        }

        //SWITCH
        switch.setOnCheckedChangeListener { _, isChecked ->
            //LPA / LPNA CHECK
            if (isChecked){
                lpa = "LPA"
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA + slider.value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            }
            else {
                lpa = "LPNA"
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA + slider.value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            }
        }

        //SLIDER
        slider.addOnChangeListener { _, value, _ ->
            //IMAGE UPDATE
            if (lpa == "LPNA")
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA + value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            else
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA + value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

            //LOG
            println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA + value.toInt()}.jpg")
        }
    }
}
