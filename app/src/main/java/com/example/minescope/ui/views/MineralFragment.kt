package com.example.minescope.ui.views

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minescope.R
import com.example.minescope.ui.adapters.SamplesListAdapter
import com.example.minescope.ui.viewmodel.MinescopeViewModel


class MineralFragment : Fragment(R.layout.fragment_mineral) {
    //ATTRIBUTES
    private val viewModel: MinescopeViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mineralName: TextView
    private lateinit var mineralChemicalFormula: TextView
    private lateinit var mineralColors: TextView
    private lateinit var mineralPleochroism: TextView
    private lateinit var mineralReliefTitle: TextView
    private lateinit var mineralRelief: TextView
    private lateinit var mineralExfoliationDirectionNameTitle: TextView
    private lateinit var mineralExfoliationDirectionName: TextView
    private lateinit var mineralExfoliationDirectionAnglesTitle: TextView
    private lateinit var mineralExfoliationDirectionAngles: TextView
    private lateinit var mineralInterferenceColorsOrderTitle: TextView
    private lateinit var mineralInterferenceColorsOrder: TextView
    private lateinit var mineralExtintionTitle: TextView
    private lateinit var mineralExtintion: TextView
    private lateinit var mineralTwinningTitle: TextView
    private lateinit var mineralTwinning: TextView
    private lateinit var mineralInterferenceFigureTitle: TextView
    private lateinit var mineralInterferenceFigure: TextView
    private lateinit var mineralOpticSignTitle: TextView
    private lateinit var mineralOpticSign: TextView
    private lateinit var mineralCrystalShapeTitle: TextView
    private lateinit var mineralCrystalShape: TextView
    private lateinit var mineralAlterationTitle: TextView
    private lateinit var mineralAlteration: TextView
    private lateinit var mineralZoningTitle: TextView
    private lateinit var mineralZoning: TextView
    private lateinit var mineralExfoliationTitle: TextView
    private lateinit var mineralExfoliation: TextView

    private lateinit var mineralReflectivityTitle: TextView
    private lateinit var mineralReflectivity: TextView
    private lateinit var mineralHardnessTitle: TextView
    private lateinit var mineralHardness: TextView
    private lateinit var mineralAnisotropyTitle: TextView
    private lateinit var mineralAnisotropy: TextView
    private lateinit var mineralInterferenceColorsTitle: TextView
    private lateinit var mineralInterferenceColors: TextView
    private lateinit var mineralInternalReflectionsTitle: TextView
    private lateinit var mineralInternalReflections: TextView
    private lateinit var mineralCleavageTitle: TextView
    private lateinit var mineralCleavage: TextView
    private lateinit var mineralAsociatedMineralsTitle: TextView
    private lateinit var mineralAsociatedMinerals: TextView

    //ON VIEW CREATED
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        mineralName = view.findViewById(R.id.mineral_name)
        mineralChemicalFormula = view.findViewById(R.id.mineral_chemical_formula)
        mineralColors = view.findViewById(R.id.mineral_colors)
        mineralPleochroism = view.findViewById(R.id.mineral_pleochroism)
        mineralReliefTitle = view.findViewById(R.id.mineral_relief_title)
        mineralRelief = view.findViewById(R.id.mineral_relief)
        mineralExfoliationDirectionNameTitle = view.findViewById(R.id.mineral_exfoliation_direction_name_title)
        mineralExfoliationDirectionName = view.findViewById(R.id.mineral_exfoliation_direction_name)
        mineralExfoliationDirectionAnglesTitle = view.findViewById(R.id.mineral_exfoliation_direction_angles_title)
        mineralExfoliationDirectionAngles = view.findViewById(R.id.mineral_exfoliation_direction_angles)
        mineralInterferenceColorsOrderTitle = view.findViewById(R.id.mineral_interference_colors_order_title)
        mineralInterferenceColorsOrder = view.findViewById(R.id.mineral_interference_colors_order)
        mineralExtintionTitle = view.findViewById(R.id.mineral_extintion_title)
        mineralExtintion = view.findViewById(R.id.mineral_extintion)
        mineralTwinningTitle = view.findViewById(R.id.mineral_twinning_title)
        mineralTwinning = view.findViewById(R.id.mineral_twinning)
        mineralInterferenceFigureTitle = view.findViewById(R.id.mineral_interference_figure_title)
        mineralInterferenceFigure = view.findViewById(R.id.mineral_interference_figure)
        mineralOpticSignTitle = view.findViewById(R.id.mineral_optic_sign_title)
        mineralOpticSign = view.findViewById(R.id.mineral_optic_sign)
        mineralCrystalShapeTitle = view.findViewById(R.id.mineral_crystal_shape_title)
        mineralCrystalShape = view.findViewById(R.id.mineral_crystal_shape)
        mineralAlterationTitle = view.findViewById(R.id.mineral_alteration_title)
        mineralAlteration = view.findViewById(R.id.mineral_alteration)
        mineralZoningTitle = view.findViewById(R.id.mineral_zoning_title)
        mineralZoning = view.findViewById(R.id.mineral_zoning)
        mineralExfoliationTitle = view.findViewById(R.id.mineral_exfoliation_title)
        mineralExfoliation = view.findViewById(R.id.mineral_exfoliation)
        mineralReflectivityTitle = view.findViewById(R.id.mineral_reflectivity_title)
        mineralReflectivity = view.findViewById(R.id.mineral_reflectivity)
        mineralHardnessTitle = view.findViewById(R.id.mineral_hardness_title)
        mineralHardness = view.findViewById(R.id.mineral_hardness)
        mineralAnisotropyTitle = view.findViewById(R.id.mineral_anisotropy_title)
        mineralAnisotropy = view.findViewById(R.id.mineral_anisotropy)
        mineralInterferenceColorsTitle = view.findViewById(R.id.mineral_interference_colors_title)
        mineralInterferenceColors = view.findViewById(R.id.mineral_interference_colors)
        mineralInternalReflectionsTitle = view.findViewById(R.id.mineral_internal_reflections_title)
        mineralInternalReflections = view.findViewById(R.id.mineral_internal_reflections)
        mineralCleavageTitle = view.findViewById(R.id.mineral_cleavage_title)
        mineralCleavage = view.findViewById(R.id.mineral_cleavage)
        mineralAsociatedMineralsTitle = view.findViewById(R.id.mineral_asociated_minerals_title)
        mineralAsociatedMinerals = view.findViewById(R.id.mineral_asociated_minerals)

        //RECYCLER VIEW SET
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)



        //loadData()
        //startFunc()
    }

    private fun startFunc(){
        val principalAdapter = SamplesListAdapter(viewModel)
        viewModel.mineralsListLD.observe(viewLifecycleOwner,{principalAdapter.setMarkerList(it) })
        recyclerView.adapter = principalAdapter
    }


    private fun loadData(){
        if (!arguments?.isEmpty!!){
            val id = arguments?.getInt("id")
            val mineral = viewModel.mineralsList.filter { it == id.toString() }[0]


            mineralName.text = ""
            mineralChemicalFormula.text = ""
            mineralColors.text = ""
            mineralPleochroism.text = ""

            if (mineral == "TRANSPARENT"){
                mineralReliefTitle.text = ""
                mineralRelief.text = ""
                mineralExfoliationDirectionNameTitle.text = ""
                mineralExfoliationDirectionName.text = ""
                mineralExfoliationDirectionAnglesTitle.text = ""
                mineralExfoliationDirectionAngles.text = ""
                mineralInterferenceColorsOrderTitle.text = ""
                mineralInterferenceColorsOrder.text = ""
                mineralExtintionTitle.text = ""
                mineralExtintion.text = ""
                mineralTwinningTitle.text = ""
                mineralTwinning.text = ""
                mineralInterferenceFigureTitle.text = ""
                mineralInterferenceFigure.text = ""
                mineralOpticSignTitle.text = ""
                mineralOpticSign.text = ""
                mineralCrystalShapeTitle.text = ""
                mineralCrystalShape.text = ""
                mineralAlterationTitle.text = ""
                mineralAlteration.text = ""
                mineralZoningTitle.text = ""
                mineralZoning.text = ""
                mineralExfoliationTitle.text = ""
                mineralExfoliation.text = ""

                mineralReflectivityTitle.visibility = View.GONE
                mineralReflectivity.visibility = View.GONE
                mineralHardnessTitle.visibility = View.GONE
                mineralHardness.visibility = View.GONE
                mineralAnisotropyTitle.visibility = View.GONE
                mineralAnisotropy.visibility = View.GONE
                mineralInterferenceColorsTitle.visibility = View.GONE
                mineralInterferenceColors.visibility = View.GONE
                mineralInternalReflectionsTitle.visibility = View.GONE
                mineralInternalReflections.visibility = View.GONE
                mineralCleavageTitle.visibility = View.GONE
                mineralCleavage.visibility = View.GONE
                mineralAsociatedMineralsTitle.visibility = View.GONE
                mineralAsociatedMinerals.visibility = View.GONE
            }
            else if (mineral == "OPAQUE"){
                mineralReflectivityTitle.text = ""
                mineralReflectivity.text = ""
                mineralHardnessTitle.text = ""
                mineralHardness.text = ""
                mineralAnisotropyTitle.text = ""
                mineralAnisotropy.text = ""
                mineralInterferenceColorsTitle.text = ""
                mineralInterferenceColors.text = ""
                mineralInternalReflectionsTitle.text = ""
                mineralInternalReflections.text = ""
                mineralCleavageTitle.text = ""
                mineralCleavage.text = ""
                mineralAsociatedMineralsTitle.text = ""
                mineralAsociatedMinerals.text = ""

                mineralReliefTitle.visibility = View.GONE
                mineralRelief.visibility = View.GONE
                mineralExfoliationDirectionNameTitle.visibility = View.GONE
                mineralExfoliationDirectionName.visibility = View.GONE
                mineralExfoliationDirectionAnglesTitle.visibility = View.GONE
                mineralExfoliationDirectionAngles.visibility = View.GONE
                mineralInterferenceColorsOrderTitle.visibility = View.GONE
                mineralInterferenceColorsOrder.visibility = View.GONE
                mineralExtintionTitle.visibility = View.GONE
                mineralExtintion.visibility = View.GONE
                mineralTwinningTitle.visibility = View.GONE
                mineralTwinning.visibility = View.GONE
                mineralInterferenceFigureTitle.visibility = View.GONE
                mineralInterferenceFigure.visibility = View.GONE
                mineralOpticSignTitle.visibility = View.GONE
                mineralOpticSign.visibility = View.GONE
                mineralCrystalShapeTitle.visibility = View.GONE
                mineralCrystalShape.visibility = View.GONE
                mineralAlterationTitle.visibility = View.GONE
                mineralAlteration.visibility = View.GONE
                mineralZoningTitle.visibility = View.GONE
                mineralZoning.visibility = View.GONE
                mineralExfoliationTitle.visibility = View.GONE
                mineralExfoliation.visibility = View.GONE

            }
        }
    }
}
