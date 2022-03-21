package com.example.minescope.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minescope.R
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

    //ON VIEW CREATED
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
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //SELECTED
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0)
                    setUpLayout(true)
                else
                    setUpLayout(false)
            }

            //RESELECTED
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            //UNSELECTED
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
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
}
