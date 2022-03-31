package com.example.minescope.ui.views

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.minescope.R
import com.example.minescope.data.models.OpaqueMineralSample
import com.example.minescope.data.models.TransparentMineralSample
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.squareup.picasso.Picasso

class SampleFragment : Fragment(R.layout.fragment_sample) {
    //ATTRIBUTES

    //Layout Elements
    private lateinit var closeBtn: ImageView
    private lateinit var image: ImageView
    private lateinit var switch: SwitchMaterial
    private lateinit var slider: Slider
    private lateinit var backwardIcon: ImageView
    private lateinit var playIcon: ImageView
    private lateinit var forwardIcon: ImageView

    //Data
    private lateinit var sampleName: TextView
    private lateinit var sampleSurname: TextView
    private lateinit var sampleDescription: TextView
    private lateinit var sampleColoration: TextView
    private lateinit var samplePleochroism: TextView
    private lateinit var sampleAbundance: TextView
    private lateinit var sampleOtherMinerals: TextView
    private lateinit var sampleReliefTitle: TextView
    private lateinit var sampleRelief: TextView
    private lateinit var sampleExfoliationTitle: TextView
    private lateinit var sampleExfoliation: TextView
    private lateinit var sampleAlterationTitle: TextView
    private lateinit var sampleAlteration: TextView
    private lateinit var sampleInterferenceColorsOrderTitle: TextView
    private lateinit var sampleInterferenceColorsOrder: TextView
    private lateinit var sampleExtinctionTitle: TextView
    private lateinit var sampleExtinction: TextView
    private lateinit var sampleTwinningTitle: TextView
    private lateinit var sampleTwinning: TextView
    private lateinit var sampleZoningTitle: TextView
    private lateinit var sampleZoning: TextView
    private lateinit var sampleCrystalShapeTitle: TextView
    private lateinit var sampleCrystalShape: TextView
    private lateinit var sampleShapeTitle: TextView
    private lateinit var sampleShape: TextView
    private lateinit var sampleCleavageTitle: TextView
    private lateinit var sampleCleavage: TextView
    private lateinit var sampleReflectivityTitle: TextView
    private lateinit var sampleReflectivity: TextView
    private lateinit var sampleHardnessTitle: TextView
    private lateinit var sampleHardness: TextView
    private lateinit var sampleAnisotropyTitle: TextView
    private lateinit var sampleAnisotropy: TextView
    private lateinit var sampleInterferenceColorsTitle: TextView
    private lateinit var sampleInterferenceColors: TextView
    private lateinit var sampleInternalReflectionsTitle: TextView
    private lateinit var sampleInternalReflections: TextView
    
    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()

    var type = false
    private val firstValueLPNA = 3544
    private val firstValueLPA = 3363
    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        closeBtn = view.findViewById(R.id.close_btn)
        image = view.findViewById(R.id.image)
        switch = view.findViewById(R.id.lpa_switch)
        slider = view.findViewById(R.id.slider)
        backwardIcon = view.findViewById(R.id.backward_icon)
        playIcon = view.findViewById(R.id.play_icon)
        forwardIcon = view.findViewById(R.id.forward_icon)
        sampleName = view.findViewById(R.id.sample_name)
        sampleSurname = view.findViewById(R.id.sample_surname)
        sampleDescription = view.findViewById(R.id.sample_description)
        sampleColoration = view.findViewById(R.id.sample_coloration)
        samplePleochroism = view.findViewById(R.id.sample_pleochroism)
        sampleAbundance = view.findViewById(R.id.sample_abundance)
        sampleOtherMinerals = view.findViewById(R.id.sample_other_minerals)
        sampleReliefTitle = view.findViewById(R.id.sample_relief_title)
        sampleRelief = view.findViewById(R.id.sample_relief)
        sampleExfoliationTitle = view.findViewById(R.id.sample_exfoliation_title)
        sampleExfoliation = view.findViewById(R.id.sample_exfoliation)
        sampleAlterationTitle = view.findViewById(R.id.sample_alteration_title)
        sampleAlteration = view.findViewById(R.id.sample_alteration)
        sampleInterferenceColorsOrderTitle = view.findViewById(R.id.sample_interference_colors_order_title)
        sampleInterferenceColorsOrder = view.findViewById(R.id.sample_interference_colors_order)
        sampleExtinctionTitle = view.findViewById(R.id.sample_extintion_title)
        sampleExtinction = view.findViewById(R.id.sample_extintion)
        sampleTwinningTitle = view.findViewById(R.id.sample_twinning_title)
        sampleTwinning = view.findViewById(R.id.sample_twinning)
        sampleZoningTitle = view.findViewById(R.id.sample_zoning_title)
        sampleZoning = view.findViewById(R.id.sample_zoning)
        sampleCrystalShapeTitle = view.findViewById(R.id.sample_crystal_shape_title)
        sampleCrystalShape = view.findViewById(R.id.sample_crystal_shape)
        sampleShapeTitle = view.findViewById(R.id.sample_shape_title)
        sampleShape = view.findViewById(R.id.sample_shape)
        sampleCleavageTitle = view.findViewById(R.id.sample_cleavage_title)
        sampleCleavage = view.findViewById(R.id.sample_cleavage)
        sampleReflectivityTitle = view.findViewById(R.id.sample_reflectivity_title)
        sampleReflectivity = view.findViewById(R.id.sample_reflectivity)
        sampleHardnessTitle = view.findViewById(R.id.sample_hardness_title)
        sampleHardness = view.findViewById(R.id.sample_hardness)
        sampleAnisotropyTitle = view.findViewById(R.id.sample_anisotropy_title)
        sampleAnisotropy = view.findViewById(R.id.sample_anisotropy)
        sampleInterferenceColorsTitle = view.findViewById(R.id.sample_interference_colors_title)
        sampleInterferenceColors = view.findViewById(R.id.sample_interference_colors)
        sampleInternalReflectionsTitle = view.findViewById(R.id.sample_internal_reflections_title)
        sampleInternalReflections = view.findViewById(R.id.sample_internal_reflections)

        loadData()

        //ON CLICK
        //Close Button
        closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        //Backward Icon
        backwardIcon.setOnClickListener {
            viewModel.shouldPlay = false
            playIcon.setImageResource(R.drawable.play_icon)
            moveWithIcons(-1)
        }

        //Play Icon
        playIcon.setOnClickListener {
            //PLAY
            if (!viewModel.shouldPlay) {
                playIcon.setImageResource(R.drawable.pause_icon)
                viewModel.shouldPlay = true

                //IMAGE LOOP
                val handler = Handler(Looper.getMainLooper())
                var count = 0

                val runnable: Runnable = object : Runnable {
                    override fun run() {
                        if (count++ <= 143 && viewModel.shouldPlay) {
                            moveWithIcons(1)
                            handler.postDelayed(this, 700)
                        }

                        if (slider.value.toInt() == 143) {
                            playIcon.setImageResource(R.drawable.play_icon)
                            viewModel.shouldPlay = false
                        }
                    }
                }
                handler.post(runnable)
            }
            //PAUSE
            else {
                playIcon.setImageResource(R.drawable.play_icon)
                viewModel.shouldPlay = false
            }
        }

        //Forward Icon
        forwardIcon.setOnClickListener {
            viewModel.shouldPlay = false
            playIcon.setImageResource(R.drawable.play_icon)
            moveWithIcons(1)
        }

        //SWITCH
        switch.setOnCheckedChangeListener { _, isChecked ->
            //LPA / LPNA CHECK
            if (isChecked){
                Picasso.get().load("${viewModel.currentLPA}${firstValueLPA + slider.value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            }
            else {
                Picasso.get().load("${viewModel.currentLPNA}${firstValueLPNA + slider.value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            }
            type = !type
        }

        //SLIDER
        slider.addOnChangeListener { _, value, _ ->
            //IMAGE UPDATE
            if (!type)
                Picasso.get().load("${viewModel.currentLPNA}${firstValueLPNA + value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)
            else
                Picasso.get().load("${viewModel.currentLPA}${firstValueLPA + value.toInt()}.jpg")
                    .noFade().placeholder(image.drawable).into(image)

            //LOG
            //println("https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/$lpa/IMG_${firstValueLPNA + value.toInt()}.jpg")
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
            //LPNA
            if (!type) {
                //VIDEO
                if (viewModel.shouldPlay) {
                    Picasso.get().load("${viewModel.currentLPNA}${firstValueLPNA+slider.value.toInt()+value}.jpg")
                        .noFade().placeholder(image.drawable).into(object : com.squareup.picasso.Target {
                            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                                Log.d("FAIL", "Bitmap Failed")
                            }

                            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                                image.setImageBitmap(bitmap)

                                //SLIDER UPDATE
                                slider.value += value
                            }

                            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                        })
                }
                //NEXT IMAGE
                else {
                    Picasso.get().load("${viewModel.currentLPNA}${firstValueLPNA+slider.value.toInt()+value}.jpg")
                        .noFade().placeholder(image.drawable).into(image)

                    //SLIDER UPDATE
                    slider.value += value
                }
            }
            //LPA
            else {
                //VIDEO
                if (viewModel.shouldPlay) {
                    Picasso.get().load("${viewModel.currentLPA}${firstValueLPA+slider.value.toInt()+value}.jpg")
                        .noFade().placeholder(image.drawable).into(object : com.squareup.picasso.Target {
                            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                                Log.d("FAIL", "Bitmap Failed")
                            }

                            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                                image.setImageBitmap(bitmap)

                                //SLIDER UPDATE
                                slider.value += value
                            }

                            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                        })
                }
                //NEXT IMAGE
                else {
                    Picasso.get().load("${viewModel.currentLPA}${firstValueLPA+slider.value.toInt()+value}.jpg")
                        .noFade().placeholder(image.drawable).into(image)

                    //SLIDER UPDATE
                    slider.value += value
                }
            }
        }
    }

    /**
     * This method is used to load the sample's data into the fragment.
     */
    private fun loadData() {
        if (!arguments?.isEmpty!!) {
            val id = arguments?.getInt("id")
            val isOpaque = arguments?.getBoolean("isOpaque")

            var opaqueMineralSample: OpaqueMineralSample? = null
            var transparentMineralSample: TransparentMineralSample? = null

            if (isOpaque!!) {
                opaqueMineralSample = viewModel.samplesOfOpaqueMineralList.filter { it.id == id }[0]
            }else{
                transparentMineralSample = viewModel.samplesOfTransparentMineralList.filter { it.id == id }[0]
            }

            if (!isOpaque){
                sampleName.text = transparentMineralSample?.name
                sampleSurname.text = transparentMineralSample?.name
                sampleDescription.text = transparentMineralSample?.alteration
                sampleColoration.text = transparentMineralSample?.coloration
                samplePleochroism.text = transparentMineralSample?.pleochroism
                sampleAbundance.text = transparentMineralSample?.abundance.toString()
                sampleOtherMinerals.text = transparentMineralSample?.otherMinerals
                sampleRelief.text = transparentMineralSample?.relief
                sampleExfoliation.text = transparentMineralSample?.exfoliation
                sampleAlteration.text = transparentMineralSample?.alteration
                sampleInterferenceColorsOrder.text = transparentMineralSample?.interferenceColorsOrder
                sampleExtinction.text = transparentMineralSample?.extinction
                sampleTwinning.text = transparentMineralSample?.twinning
                sampleZoning.text = transparentMineralSample?.zoning
                sampleCrystalShape.text = transparentMineralSample?.crystalShape
                //IMAGE INITIALIZATION
                Picasso.get().load(transparentMineralSample?.imageLPNA+"$firstValueLPNA.jpg")
                    .noFade().placeholder(image.drawable).into(image)
                viewModel.currentLPA = transparentMineralSample?.imageLPA!!
                viewModel.currentLPNA = transparentMineralSample.imageLPNA
                //LAYOUT UPDATE
                sampleShapeTitle.visibility = View.GONE
                sampleShape.visibility = View.GONE
                sampleCleavageTitle.visibility = View.GONE
                sampleCleavage.visibility = View.GONE
                sampleReflectivityTitle.visibility = View.GONE
                sampleReflectivity.visibility = View.GONE
                sampleHardnessTitle.visibility = View.GONE
                sampleHardness.visibility = View.GONE
                sampleAnisotropyTitle.visibility = View.GONE
                sampleAnisotropy.visibility = View.GONE
                sampleInterferenceColorsTitle.visibility = View.GONE
                sampleInterferenceColors.visibility = View.GONE
                sampleInternalReflectionsTitle.visibility = View.GONE
                sampleInternalReflections.visibility = View.GONE
            }
            else if (isOpaque){
                sampleName.text = opaqueMineralSample?.name
                sampleSurname.text = opaqueMineralSample?.name
                sampleDescription.text = opaqueMineralSample?.cleavage
                sampleColoration.text = opaqueMineralSample?.coloration
                samplePleochroism.text = opaqueMineralSample?.pleochroism
                sampleAbundance.text = opaqueMineralSample?.abundance.toString()
                sampleOtherMinerals.text = opaqueMineralSample?.otherMinerals
                sampleShape.text = opaqueMineralSample?.shape
                sampleCleavage.text = opaqueMineralSample?.cleavage
                sampleReflectivity.text = opaqueMineralSample?.reflectivity
                sampleHardness.text = opaqueMineralSample?.hardness
                sampleAnisotropy.text = opaqueMineralSample?.anisotropy
                sampleInterferenceColors.text = opaqueMineralSample?.interferenceColors
                sampleInternalReflections.text = opaqueMineralSample?.internalReflections
                //IMAGE INITIALIZATION
                Picasso.get().load(opaqueMineralSample?.imageLPNA+"$firstValueLPNA.jpg")
                    .noFade().placeholder(image.drawable).into(image)
                viewModel.currentLPA = opaqueMineralSample?.imageLPA!!
                viewModel.currentLPNA = opaqueMineralSample.imageLPNA
                //LAYOUT UPDATE
                sampleReliefTitle.visibility = View.GONE
                sampleRelief.visibility = View.GONE
                sampleExfoliationTitle.visibility = View.GONE
                sampleExfoliation.visibility = View.GONE
                sampleAlterationTitle.visibility = View.GONE
                sampleAlteration.visibility = View.GONE
                sampleInterferenceColorsOrderTitle.visibility = View.GONE
                sampleInterferenceColorsOrder.visibility = View.GONE
                sampleExtinctionTitle.visibility = View.GONE
                sampleExtinction.visibility = View.GONE
                sampleTwinningTitle.visibility = View.GONE
                sampleTwinning.visibility = View.GONE
                sampleZoningTitle.visibility = View.GONE
                sampleZoning.visibility = View.GONE
                sampleCrystalShapeTitle.visibility = View.GONE
                sampleCrystalShape.visibility = View.GONE
            }
        }
    }
}
