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
    private lateinit var switch: SwitchMaterial
    private lateinit var slider: Slider
    private lateinit var backwardIcon: ImageView
    private lateinit var forwardIcon: ImageView

    //URL VARIABLES
    private val firstValueLPNA = 3544
    private val firstValueLPA = 3363
    private var lpa = "LPNA"

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        image = view.findViewById(R.id.image)
        switch = view.findViewById(R.id.lpa_switch)
        slider = view.findViewById(R.id.slider)
        backwardIcon = view.findViewById(R.id.backward_icon)
        forwardIcon = view.findViewById(R.id.forward_icon)

        //IMAGE INITIALIZATION
        Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA}.jpg")
            .noFade().placeholder(image.drawable).into(image)

        //ON CLICK
        //Backward Icon
        backwardIcon.setOnClickListener {
            moveWithIcons(-1)
        }

        //Forward Icon
        forwardIcon.setOnClickListener {
            moveWithIcons(1)
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

    //METHODS
    /**
     * This method is used to go to the next or the previous photo by tapping the backward or forward icons.
     * @param value It indicates if the movement is backwards or forwards. It can be 1 or -1.
     */
    private fun moveWithIcons(value: Int) {
        //IMAGE UPDATE
        if (slider.value.toInt()+value in 0..143) {
            if (lpa == "LPNA") {
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA+slider.value.toInt()+value}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

                //SLIDER UPDATE
                slider.value += value

                //LOG
                println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA+slider.value.toInt()}.jpg")
            }
            else {
                Picasso.get().load("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA+slider.value.toInt()+value}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

                //SLIDER UPDATE
                slider.value += value

                //LOG
                println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPA+slider.value.toInt()}.jpg")
            }
        }
    }
}
