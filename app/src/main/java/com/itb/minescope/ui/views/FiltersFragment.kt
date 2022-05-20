package com.itb.minescope.ui.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itb.minescope.data.models.FilterOption
import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.ui.adapters.FilterOptionsAdapter
import com.itb.minescope.ui.viewmodel.MinescopeViewModel
import com.google.android.material.tabs.TabLayout
import com.itb.minescope.R

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
        if (!viewModel.isTransparentFilters) {
            tabLayout.getTabAt(1)!!.select()
            setUpLayout(false)
        }

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

        //FILTER NAME SET UP
        relief.text = relief.text.toString()+": "
        colorTransparent.text = colorTransparent.text.toString()+": "
        pleochroismTransparent.text = pleochroismTransparent.text.toString()+": "
        cleavageDirections.text = cleavageDirections.text.toString()+": "
        cleavageAngle.text = cleavageAngle.text.toString()+": "
        interferenceColorOrder.text = interferenceColorOrder.text.toString()+": "
        extinction.text = extinction.text.toString()+": "
        twinning.text = twinning.text.toString()+": "
        interferenceFigure.text = interferenceFigure.text.toString()+": "
        opticalSign.text = opticalSign.text.toString()+": "
        colorOpaque.text = colorOpaque.text.toString()+": "
        reflectance.text = reflectance.text.toString()+": "
        pleochroismOpaque.text = pleochroismOpaque.text.toString()+": "
        polishingHardness.text = polishingHardness.text.toString()+": "
        anisotropism.text = anisotropism.text.toString()+": "
        interferenceColors.text = interferenceColors.text.toString()+": "
        internalReflections.text = internalReflections.text.toString()+": "

        //ON CLICK

        //Logo
        logo.setOnClickListener {
            //NAVIGATION
            findNavController().navigate(R.id.listFragment)
        }

        //Check Icon
        checkIcon.setOnClickListener {
            if (viewModel.isTransparentFilters) {
                viewModel.transparentFilters = TransparentMineral(0, "",
                    colorTransparent.text.toString().split(": ").toMutableList()[1],
                    pleochroismTransparent.text.toString().split(": ").toMutableList()[1], "",
                    relief.text.toString().split(": ").toMutableList()[1],
                    cleavageDirections.text.toString().split(": ").toMutableList()[1],
                    cleavageAngle.text.toString().split(": ").toMutableList()[1],
                    interferenceColorOrder.text.toString().split(": ").toMutableList()[1],
                    extinction.text.toString().split(": ").toMutableList()[1],
                    twinning.text.toString().split(": ").toMutableList()[1],
                    interferenceFigure.text.toString().split(": ").toMutableList()[1],
                    opticalSign.text.toString().split(": ").toMutableList()[1], "", "", "", "", null)
                Log.d("FILTERS TRANSPARENT", viewModel.transparentFilters.toString())
            }
            else {
                viewModel.opaqueFilters = OpaqueMineral(0, "",
                    colorOpaque.text.toString().split(": ").toMutableList()[1],
                    pleochroismOpaque.text.toString().split(": ").toMutableList()[1], "",
                    reflectance.text.toString().split(": ").toMutableList()[1],
                    polishingHardness.text.toString().split(": ").toMutableList()[1],
                    anisotropism.text.toString().split(": ").toMutableList()[1],
                    interferenceColors.text.toString().split(": ").toMutableList()[1],
                    internalReflections.text.toString().split(": ").toMutableList()[1], "", "", null)
                Log.d("FILTERS OPAQUE", viewModel.opaqueFilters.toString())
            }

            findNavController().navigate(R.id.listFragment)
        }

        //Relief
        relief.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.low), getString(R.string.low_relief)),
                FilterOption(getString(R.string.medium), getString(R.string.medium_relief)),
                FilterOption(getString(R.string.high), getString(R.string.high_relief))
            )

            buildFilterDialog(getString(R.string.relief), options, relief)
        }

        //Color Transparent
        colorTransparent.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.colorless), getString(R.string.colorless_color)),
                FilterOption(getString(R.string.yellow), getString(R.string.yellow_color)),
                FilterOption(getString(R.string.pink), getString(R.string.pink_color)),
                FilterOption(getString(R.string.blue), getString(R.string.blue_color)),
                FilterOption(getString(R.string.green), getString(R.string.green_color)),
                FilterOption(getString(R.string.brown), getString(R.string.brown_color))
            )

            buildFilterDialog(getString(R.string.color), options, colorTransparent)
        }

        //Pleochroism Transparent
        pleochroismTransparent.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.without), getString(R.string.without_pleochroism)),
                FilterOption(getString(R.string.soft), getString(R.string.soft_pleochroism)),
                FilterOption(getString(R.string.strong), getString(R.string.strong_pleochroism))
            )

            buildFilterDialog(getString(R.string.pleochroism), options, pleochroismTransparent)
        }

        //Cleavage Directions
        cleavageDirections.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption("0", getString(R.string.zero_ncd)),
                FilterOption("1", getString(R.string.one_ncd)),
                FilterOption("2", getString(R.string.two_ncd)),
                FilterOption(getString(R.string.three_or_more), getString(R.string.three_or_more_ncd))
            )

            buildFilterDialog(getString(R.string.num_cleavage_directions), options, cleavageDirections)
        }

        //Cleavage Angle
        cleavageAngle.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.right), "(≈ 90°)"),
                FilterOption(getString(R.string.acute), "(≠ 90°)")
            )

            buildFilterDialog(getString(R.string.angle_cleavage), options, cleavageAngle)
        }

        //Interference Color Order
        interferenceColorOrder.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.isotropic), getString(R.string.isotropic_ico)),
                FilterOption(getString(R.string.first), getString(R.string.first_ico)),
                FilterOption(getString(R.string.second), getString(R.string.second_ico)),
                FilterOption(getString(R.string.third), getString(R.string.third_ico)),
                FilterOption(getString(R.string.fourth), getString(R.string.fourth_ico)),
                FilterOption(getString(R.string.anomalous), getString(R.string.anomalous_ico)),
                FilterOption(getString(R.string.masked), getString(R.string.masked_ico))
            )

            buildFilterDialog(getString(R.string.interference_color_order), options, interferenceColorOrder)
        }

        //Extinction
        extinction.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.total), getString(R.string.total_extinction)),
                FilterOption(getString(R.string.right_ex), getString(R.string.right_extinction)),
                FilterOption(getString(R.string.oblique), getString(R.string.oblique_extinction)),
                FilterOption(getString(R.string.undulose), getString(R.string.undulose_extinction))
            )

            buildFilterDialog(getString(R.string.extinction), options, extinction)
        }

        //Twinning
        twinning.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.without), getString(R.string.without_twinning)),
                FilterOption(getString(R.string.simple), getString(R.string.simple_twinning)),
                FilterOption(getString(R.string.polysynthetic), getString(R.string.polysynthetic_twinning)),
                FilterOption(getString(R.string.crossed), getString(R.string.crossed_twinning)),
                FilterOption(getString(R.string.cyclic), getString(R.string.cyclic_twinning))
            )

            buildFilterDialog(getString(R.string.twinning), options, twinning)
        }

        //Interference Figure
        interferenceFigure.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.uniaxial), "", R.drawable.interference_figure_1, R.drawable.interference_figure_2, R.drawable.interference_figure_3),
                FilterOption(getString(R.string.biaxial), "", R.drawable.interference_figure_4, R.drawable.interference_figure_5, R.drawable.interference_figure_6)
            )

            buildFilterDialog(getString(R.string.interference_figure), options, interferenceFigure)
        }

        //Optical Sign
        opticalSign.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.positive), "", R.drawable.optical_sign_2, R.drawable.optical_sign_3, R.drawable.optical_sign_1),
                FilterOption(getString(R.string.negative), "", R.drawable.optical_sign_5, R.drawable.optical_sign_6, R.drawable.optical_sign_4)
            )

            buildFilterDialog(getString(R.string.optical_sign), options, opticalSign)
        }

        //Color Opaque
        colorOpaque.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.white), getString(R.string.white_color)),
                FilterOption(getString(R.string.yellow), getString(R.string.yellow_color)),
                FilterOption(getString(R.string.pink), getString(R.string.pink_color_opaque)),
                FilterOption(getString(R.string.blue), getString(R.string.blue_color_opaque)),
                FilterOption(getString(R.string.green), getString(R.string.green_color)),
                FilterOption(getString(R.string.brown), getString(R.string.brown_color)),
                FilterOption(getString(R.string.gray), getString(R.string.gray_color))
            )

            buildFilterDialog(getString(R.string.color), options, colorOpaque)
        }

        //Reflectance
        reflectance.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.low_ref), getString(R.string.low_reflectance)),
                FilterOption(getString(R.string.medium_ref), getString(R.string.medium_reflectance)),
                FilterOption(getString(R.string.strong_ref), getString(R.string.strong_reflectance)),
                FilterOption(getString(R.string.very_strong_ref), getString(R.string.very_strong_reflectance))
            )

            buildFilterDialog(getString(R.string.reflectance), options, reflectance)
        }

        //Pleochroism Opaque
        pleochroismOpaque.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.without_or_weak), getString(R.string.without_or_weak_pleochroism)),
                FilterOption(getString(R.string.strong_pleo), getString(R.string.strong_pleochroism_opaque))
            )

            buildFilterDialog(getString(R.string.pleochroism), options, pleochroismOpaque)
        }

        //Polishing Hardness
        polishingHardness.setOnClickListener {
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.soft_pol_hard), getString(R.string.soft_polishing_hardness)),
                FilterOption(getString(R.string.medium_pol_hard), getString(R.string.medium_polishing_hardness)),
                FilterOption(getString(R.string.hard_pol_hard), getString(R.string.hard_polishing_hardness))
            )

            buildFilterDialog(getString(R.string.polishing_hardness), options, polishingHardness)
        }

        //Anisotropism
        anisotropism.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.isotropic), getString(R.string.isotropic_anisotropism)),
                FilterOption(getString(R.string.moderately_anisotropic), getString(R.string.moderately_anisotropic_anisotropism)),
                FilterOption(getString(R.string.strongly_anisotropic), getString(R.string.strongly_anisotropic_anisotropism))
            )

            buildFilterDialog(getString(R.string.anisotropism), options, anisotropism)
        }

        //Interference Colors
        interferenceColors.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.white), getString(R.string.white_color)),
                FilterOption(getString(R.string.yellow), getString(R.string.yellow_color)),
                FilterOption(getString(R.string.pink), getString(R.string.pink_color)),
                FilterOption(getString(R.string.purple), getString(R.string.purple_color)),
                FilterOption(getString(R.string.blue), getString(R.string.blue_color_opaque)),
                FilterOption(getString(R.string.green), getString(R.string.green_color)),
                FilterOption(getString(R.string.brown), getString(R.string.brown_color)),
                FilterOption(getString(R.string.gray), getString(R.string.gray_color)),
                FilterOption(getString(R.string.masked_inter_reflect), getString(R.string.masked_color)),
                FilterOption(getString(R.string.without), getString(R.string.without_color))
            )

            buildFilterDialog(getString(R.string.interference_colors), options, interferenceColors)
        }

        //Internal reflections
        internalReflections.setOnClickListener{
            //OPTIONS LIST
            val options = listOf(
                FilterOption(getString(R.string.yes), getString(R.string.yes_ir)),
                FilterOption(getString(R.string.no), getString(R.string.no_ir))
            )

            buildFilterDialog(getString(R.string.internal_reflections), options, internalReflections)
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
            title.text = getString(R.string.transparent_minerals_filters_title)

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
            title.text = getString(R.string.opaque_minerals_filters_title)

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
