package com.example.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.minescope.R
import com.example.minescope.viewmodel.MinescopeViewModel
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
    private val viewModel: MinescopeViewModel by activityViewModels()
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
    private lateinit var sampleExtintionTitle: TextView
    private lateinit var sampleExtintion: TextView
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
        sampleExtintionTitle = view.findViewById(R.id.sample_extintion_title)
        sampleExtintion = view.findViewById(R.id.sample_extintion)
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

    private fun loadData(){
        if (!arguments?.isEmpty!!){
            val id = arguments?.getInt("id")
            val mineral = viewModel.mineralsList.filter { it == id.toString() }[0]

            sampleName.text = ""
            sampleSurname.text = ""
            sampleDescription.text = ""
            sampleColoration.text = ""
            samplePleochroism.text = ""
            sampleAbundance.text = ""
            sampleOtherMinerals.text = ""

            if (mineral == "TRANSPARENT"){
                sampleReliefTitle.text = ""
                sampleRelief.text = ""
                sampleExfoliationTitle.text = ""
                sampleExfoliation.text = ""
                sampleAlterationTitle.text = ""
                sampleAlteration.text = ""
                sampleInterferenceColorsOrderTitle.text = ""
                sampleInterferenceColorsOrder.text = ""
                sampleExtintionTitle.text = ""
                sampleExtintion.text = ""
                sampleTwinningTitle.text = ""
                sampleTwinning.text = ""
                sampleZoningTitle.text = ""
                sampleZoning.text = ""
                sampleCrystalShapeTitle.text = ""
                sampleCrystalShape.text = ""

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
            else if (mineral == "OPAQUE"){
                sampleShapeTitle.text = ""
                sampleShape.text = ""
                sampleCleavageTitle.text = ""
                sampleCleavage.text = ""
                sampleReflectivityTitle.text = ""
                sampleReflectivity.text = ""
                sampleHardnessTitle.text = ""
                sampleHardness.text = ""
                sampleAnisotropyTitle.text = ""
                sampleAnisotropy.text = ""
                sampleInterferenceColorsTitle.text = ""
                sampleInterferenceColors.text = ""
                sampleInternalReflectionsTitle.text = ""
                sampleInternalReflections.text = ""

                sampleReliefTitle.visibility = View.GONE
                sampleRelief.visibility = View.GONE
                sampleExfoliationTitle.visibility = View.GONE
                sampleExfoliation.visibility = View.GONE
                sampleAlterationTitle.visibility = View.GONE
                sampleAlteration.visibility = View.GONE
                sampleInterferenceColorsOrderTitle.visibility = View.GONE
                sampleInterferenceColorsOrder.visibility = View.GONE
                sampleExtintionTitle.visibility = View.GONE
                sampleExtintion.visibility = View.GONE
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
