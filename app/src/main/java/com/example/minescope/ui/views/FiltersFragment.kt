package com.example.minescope.ui.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.data.models.FilterOption
import com.example.minescope.ui.adapters.FilterOptionsAdapter
import com.example.minescope.ui.viewmodel.MinescopeViewModel
import com.google.android.material.tabs.TabLayout

class FiltersFragment : Fragment(R.layout.fragment_filters) {
    //ATTRIBUTES
    private lateinit var logo: ImageView
    private lateinit var checkIcon: ImageView
    private lateinit var title: TextView
    private lateinit var tabLayout: TabLayout

    //Filters (Transparent)
    private lateinit var relief: TextView
    private lateinit var colorTransparent: TextView
    private lateinit var pleochroismTransparent: TextView
    private lateinit var cleavageDirections: TextView
    private lateinit var cleavageAngle: TextView
    private lateinit var interferenceColorOrder: TextView
    private lateinit var extinction: TextView
    private lateinit var twinning: TextView
    private lateinit var interferenceFigure: TextView
    private lateinit var opticalSign: TextView

    //Filters (Opaque)
    private lateinit var colorOpaque: TextView
    private lateinit var reflectance: TextView
    private lateinit var pleochroismOpaque: TextView
    private lateinit var polishingHardness: TextView
    private lateinit var anisotropism: TextView
    private lateinit var interferenceColors: TextView
    private lateinit var internalReflections: TextView

    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()

    //ON VIEW CREATED
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //IDs
        logo = view.findViewById(R.id.logo)
        checkIcon = view.findViewById(R.id.check_icon)
        title = view.findViewById(R.id.title)
        tabLayout = view.findViewById(R.id.tab_layout)
        relief = view.findViewById(R.id.relief_filter)
        colorTransparent = view.findViewById(R.id.color_transparent_filter)
        pleochroismTransparent = view.findViewById(R.id.pleochroism_transparent_filter)
        cleavageDirections = view.findViewById(R.id.cleavage_directions_filter)
        cleavageAngle = view.findViewById(R.id.cleavage_angle_filter)
        interferenceColorOrder = view.findViewById(R.id.interference_color_order_filter)
        extinction = view.findViewById(R.id.extinction_filter)
        twinning = view.findViewById(R.id.twinning_filter)
        interferenceFigure = view.findViewById(R.id.interference_figure_filter)
        opticalSign = view.findViewById(R.id.optical_sign_filter)
        colorOpaque = view.findViewById(R.id.color_opaque_filter)
        reflectance = view.findViewById(R.id.reflectance_filter)
        pleochroismOpaque = view.findViewById(R.id.pleochroism_opaque_filter)
        polishingHardness = view.findViewById(R.id.polishing_hardness_filter)
        anisotropism = view.findViewById(R.id.anisotropism_filter)
        interferenceColors = view.findViewById(R.id.interference_colors_filter)
        internalReflections = view.findViewById(R.id.internal_reflections_filter)

        //TABS
        if (!viewModel.isTransparentFilters)
            tabLayout.getTabAt(1)!!.select()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //SELECTED
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    setUpLayout(true)
                    viewModel.isTransparentFilters = true
                }
                else {
                    setUpLayout(false)
                    viewModel.isTransparentFilters = false
                }
            }

            //RESELECTED
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            //UNSELECTED
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        //ON CLICK

        //Logo
        logo.setOnClickListener {
            //NAVIGATION
            findNavController().popBackStack()
        }

        //Relief
        relief.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Low", "The contour line of the mineral can not be seen"),
                FilterOption("Medium", "The contour line of the mineral is poorly marked"),
                FilterOption("High", "The contour line of the mineral can easily be seen"))

            buildFilterDialog("Relief", options, relief)
        }

        //Color Transparent
        colorTransparent.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Colorless", "Includes all shades of gray and white (by the white light of the microscope), which may appear cloudy by inclusions and alteration"),
                FilterOption("Yellow", "Yellowish tones"),
                FilterOption("Pink", "Pinkish tones"),
                FilterOption("Blue", "Lilac and bluish tones"),
                FilterOption("Green", "Greenish tones"),
                FilterOption("Brown", "Brown, orange and reddish tones"))

            buildFilterDialog("Color", options, colorTransparent)
        }

        //Pleochroism Transparent
        pleochroismTransparent.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Without", "The color / tone of the mineral does not change when the orientation of the crystal changes with respect to the polarized incident light"),
                FilterOption("Soft", "The color / tone of the crystal varies slightly as its orientation changes with respect to the polarized incident light"),
                FilterOption("Strong", "The color / tone varies very markedly when the orientation of the crystal changes with respect to the polarized incident light"))

            buildFilterDialog("Pleochroism", options, pleochroismTransparent)
        }

        //Cleavage Directions
        cleavageDirections.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("0", "Absence of regular discontinuities in any section of the mineral, parallel to each other and related to the crystalline structure of the mineral"),
                FilterOption("1", "Presence of only 1 direction of discontinuities parallel to each other and related to crystallographic directions of the mineral, but there may be sections without any direction of exfoliation"),
                FilterOption("2", "Presence of up to 2 directions of discontinuities parallel to each other and related to crystallographic directions of the mineral (1 direction in elongated sections, 2 directions in other sections)"),
                FilterOption("3 or more", "Presence of up to 3 or more directions of discontinuities parallel to each other and related to crystallographic directions of the mineral (two or more exfoliation directions can be seen in all sections)"))

            buildFilterDialog("Number of cleavage directions", options, cleavageDirections)
        }

        //Cleavage Angle
        cleavageAngle.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Right", "(≈ 90°)"),
                FilterOption("Acute", "(≠ 90°)"))

            buildFilterDialog("Angle of cleavage", options, cleavageAngle)
        }

        //Interference Color Order
        interferenceColorOrder.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Isotropic", "Absence of colors, it looks black when interposed the analyzer"),
                FilterOption("1st", "Greyish, white or yellow colors of 1st order are observed"),
                FilterOption("2nd", "Presence of interference colors up to 2nd order (strong colors)"),
                FilterOption("3rd", "Presence of interference colors up to 3rd order (light tones)"),
                FilterOption("4th", "Presence of interference colors up to 4th order (pastel colors)"),
                FilterOption("Anomalous", "Blues or purples that do not correspond to any order"),
                FilterOption("Masked", "The color of the mineral itself hides the interference color"))

            buildFilterDialog("Interference color (order)", options, interferenceColorOrder)
        }

        //Extinction
        extinction.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Total", "Complete extinction (light does not pass at any position of the crystal with respect to incident light), is observed in basal sections or in any section of isotropic minerals"),
                FilterOption("Right", "No light passes when the longitudinal sections are oriented parallel to the polarization directions"),
                FilterOption("Oblique", "No light passes when the longitudinal sections of the minerals are at a certain angle of the polarization directions"),
                FilterOption("Undulose", "Anomalous extinction in which the crystal does not extinguish completely and homogeneously, by changing the orientation of the crystal with respect to the incident light the crystal gradually extinguishes along the crystal, appears with the deformation of the crystal"))

            buildFilterDialog("Extinction", options, extinction)
        }

        //Twinning
        twinning.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Without", "Absence of twinnings"),
                FilterOption("Simple", "Formed by two crystals"),
                FilterOption("Polysynthetic", "Three or more crystalline planes are repeated alternately, according to the same law of twinning and with parallel twinning planes"),
                FilterOption("Crossed", "Polysynthetic twinning in two directions and blurred"),
                FilterOption("Cyclic", "When all the individuals of the twin are at a point"))

            buildFilterDialog("Twinning", options, twinning)
        }

        //Interference Figure
        interferenceFigure.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Uniaxial", "", R.drawable.interference_figure_1, R.drawable.interference_figure_2, R.drawable.interference_figure_3),
                FilterOption("Biaxial", "", R.drawable.interference_figure_4, R.drawable.interference_figure_5, R.drawable.interference_figure_6))

            buildFilterDialog("Interference figure", options, interferenceFigure)
        }

        //Optical Sign
        opticalSign.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Positive", "", R.drawable.optical_sign_2, R.drawable.optical_sign_3, R.drawable.optical_sign_1),
                FilterOption("Negative", "", R.drawable.optical_sign_5, R.drawable.optical_sign_6, R.drawable.optical_sign_4))

            buildFilterDialog("Optical sign", options, opticalSign)
        }

        //Color Opaque
        colorOpaque.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("White", "Whitish tones"),
                FilterOption("Yellow", "Yellowish tones"),
                FilterOption("Pink", "Beige, lilac and pinkish tones"),
                FilterOption("Blue", "Bluish tones"),
                FilterOption("Green", "Greenish tones"),
                FilterOption("Brown", "Brown, orange and reddish tones"),
                FilterOption("Gray", "Grayish tones that vary from dark to lighter"))

            buildFilterDialog("Color", options, colorOpaque)
        }

        //Reflectance
        reflectance.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Low", "< 20%, little gleaming, it looks very dark"),
                FilterOption("Medium", "20 - 40%, moderately gleaming"),
                FilterOption("Strong", "40 - 60%, very gleaming"),
                FilterOption("Very strong", "> 60%, extremely gleaming"))

            buildFilterDialog("Reflectance", options, reflectance)
        }

        //Pleochroism Opaque
        pleochroismOpaque.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Without or weak", "The tone of the color does not change or change very little when the orientation of the glass changes with respect to the polarized incident light"),
                FilterOption("Strong", "Change the tone of the color by changing the orientation with respect to the polarized incident light"))

            buildFilterDialog("Pleochroism", options, pleochroismOpaque)
        }

        //Polishing Hardness
        polishingHardness.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Soft", "Crystals with a very marked scratch due to polishing and correspond to soft minerals (hardness less than 3 on the Mohs scale)"),
                FilterOption("Medium", "Crystals with a slightly marked scratch due to polishing and correspond to medium hard minerals (hardness between 3 and 5 on the Mohs scale)"),
                FilterOption("Hard", "Crystals with a scratch little or nothing marked due to polishing and correspond to hard minerals (hardness higher than 5 on the Mohs scale)"))

            buildFilterDialog("Polishing hardness", options, polishingHardness)
        }

        //Anisotropism
        anisotropism.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Isotropic", "Does not reflect light when interposing the analyzer, section or mineral with high symmetry"),
                FilterOption("Moderately anisotropic", "Little or moderately anisotropic, it reflects the light when interposing the analyzer and varies a lot of color by changing the orientation of the crystal with respect to the incident light"),
                FilterOption("Strongly anisotropic", "Very anisotropic, reflects light when interposing the analyzer and varies a lot of color by changing the orientation of the crystal with respect to incident light"))

            buildFilterDialog("Anisotropism", options, anisotropism)
        }

        //Interference Colors
        interferenceColors.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("White", "Whitish tones"),
                FilterOption("Yellow", "Yellowish tones"),
                FilterOption("Pink", "Pinkish tones"),
                FilterOption("Lilac", "Lilac tones"),
                FilterOption("Blue", "Bluish tones"),
                FilterOption("Green", "Greenish tones"),
                FilterOption("Brown", "Brown, orange and reddish tones"),
                FilterOption("Gray", "Grayish tones that vary from dark to lighter"),
                FilterOption("Masked for internal reflections", "The colors of interference are not well appreciated due to the presence of internal reflections"),
                FilterOption("Without", "Does not present interfering colors"))

            buildFilterDialog("Interference colors", options, interferenceColors)
        }

        //Internal reflections
        internalReflections.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("Yes", "Presence of light sparks when interposing the analyzer"),
                FilterOption("No", "Absence of sparks of light when interposing the analyzer"))

            buildFilterDialog("Internal reflections", options, internalReflections)
        }
    }

    //METHODS
    /**
     * This method is used to hide or show different attributes depending on if they are from
     * transparent or opaque minerals.
     * @param isTransparent
     */
    @SuppressLint("SetTextI18n")
    private fun setUpLayout(isTransparent: Boolean) {
        if (isTransparent) {
            title.text = "Transparent mineral identifiers"

            relief.visibility = View.VISIBLE
            colorTransparent.visibility = View.VISIBLE
            pleochroismTransparent.visibility = View.VISIBLE
            cleavageDirections.visibility = View.VISIBLE
            cleavageAngle.visibility = View.VISIBLE
            interferenceColorOrder.visibility = View.VISIBLE
            extinction.visibility = View.VISIBLE
            twinning.visibility = View.VISIBLE
            interferenceFigure.visibility = View.VISIBLE
            opticalSign.visibility = View.VISIBLE

            colorOpaque.visibility = View.GONE
            reflectance.visibility = View.GONE
            pleochroismOpaque.visibility = View.GONE
            polishingHardness.visibility = View.GONE
            anisotropism.visibility = View.GONE
            interferenceColors.visibility = View.GONE
            internalReflections.visibility = View.GONE
        }
        else {
            title.text = "Opaque mineral identifiers"

            relief.visibility = View.GONE
            colorTransparent.visibility = View.GONE
            pleochroismTransparent.visibility = View.GONE
            cleavageDirections.visibility = View.GONE
            cleavageAngle.visibility = View.GONE
            interferenceColorOrder.visibility = View.GONE
            extinction.visibility = View.GONE
            twinning.visibility = View.GONE
            interferenceFigure.visibility = View.GONE
            opticalSign.visibility = View.GONE

            colorOpaque.visibility = View.VISIBLE
            reflectance.visibility = View.VISIBLE
            pleochroismOpaque.visibility = View.VISIBLE
            polishingHardness.visibility = View.VISIBLE
            anisotropism.visibility = View.VISIBLE
            interferenceColors.visibility = View.VISIBLE
            internalReflections.visibility = View.VISIBLE
        }
    }

    /**
     * This method is used to build a filter's dialog.
     * @param filterName Filter (e.g. relief, color...)
     * @param options Filter's options (e.g. low, medium, high...)
     * @param textView TextView
     */
    private fun buildFilterDialog(filterName: String, options: List<FilterOption>, textView: TextView) {
        //CUSTOM DIALOG
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
        val viewDialog = layoutInflater.inflate(R.layout.custom_filter_dialog, null)
        builder.setView(viewDialog)
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //Set to Transparent to only see the custom bg.
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.show()

        //ATTRIBUTES
        val filter: TextView = viewDialog.findViewById(R.id.filter)
        val recyclerView: RecyclerView = viewDialog.findViewById(R.id.recycler_view)

        //SET UP
        filter.text = filterName

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = FilterOptionsAdapter(options, textView, filter.text.toString(), dialog)
    }
}
