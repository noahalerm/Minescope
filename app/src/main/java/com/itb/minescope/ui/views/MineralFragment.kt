package com.itb.minescope.ui.views

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itb.minescope.R
import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.OpaqueMineralSample
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.data.models.TransparentMineralSample
import com.itb.minescope.ui.adapters.SamplesListAdapter
import com.itb.minescope.ui.viewmodel.MinescopeViewModel


class MineralFragment : Fragment(R.layout.fragment_mineral) {
    //ATTRIBUTES

    //Layout Elements
    private lateinit var closeBtn: ImageView
    private lateinit var recyclerView: RecyclerView

    //Data
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
    private lateinit var mineralAssociatedMineralsTitle: TextView
    private lateinit var mineralAssociatedMinerals: TextView

    //View Model
    private val viewModel: MinescopeViewModel by activityViewModels()

    //ON VIEW CREATED
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //IDs
        closeBtn = view.findViewById(R.id.close_btn)
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
        mineralAssociatedMineralsTitle = view.findViewById(R.id.mineral_asociated_minerals_title)
        mineralAssociatedMinerals = view.findViewById(R.id.mineral_asociated_minerals)

        //RECYCLER VIEW SET UP
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        loadData()

        //ON CLICK
        closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    /**
     * This method is used to load the mineral's data into the fragment.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadData(){
        if (!arguments?.isEmpty!!){
            val id = arguments?.getInt("id")
            val isOpaque = arguments?.getBoolean("isOpaque")

            var opaqueMineral: OpaqueMineral? = null
            var transparentMineral: TransparentMineral? = null

            if (isOpaque!!) {
                 opaqueMineral = viewModel.opaqueMineralsList.filter { it.id == id }[0]
            }else{
                transparentMineral = viewModel.transparentMineralsList.filter { it.id == id }[0]
            }

            if (!isOpaque){
                mineralName.text = transparentMineral?.name
                mineralChemicalFormula.text = Html.fromHtml(transparentMineral?.chemicalFormula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                mineralColors.text = transparentMineral?.colors
                mineralPleochroism.text = transparentMineral?.pleochroism
                mineralRelief.text = transparentMineral?.relief
                mineralExfoliationDirectionName.text = transparentMineral?.exfoliationDirectionName
                mineralExfoliationDirectionAngles.text = transparentMineral?.exfoliationDirectionAngles
                mineralInterferenceColorsOrder.text = transparentMineral?.interferenceColorsOrder
                mineralExtintion.text = transparentMineral?.extinction
                mineralTwinning.text = transparentMineral?.twinning
                mineralInterferenceFigure.text = transparentMineral?.interferenceFigure
                mineralOpticSign.text = transparentMineral?.opticSign
                mineralCrystalShape.text = transparentMineral?.crystalShape
                mineralAlteration.text = transparentMineral?.alteration
                mineralZoning.text = transparentMineral?.zoning
                mineralExfoliation.text = transparentMineral?.exfoliation
                val principalAdapter = SamplesListAdapter(false)
                viewModel.samplesOfTransparentMineralListLD.observe(viewLifecycleOwner,{principalAdapter.setTransparentMineralSamplesList(
                    it.filter { min -> min.mineralId == id } as MutableList<TransparentMineralSample>) })
                recyclerView.adapter = principalAdapter

                //LAYOUT UPDATE
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
                mineralAssociatedMineralsTitle.visibility = View.GONE
                mineralAssociatedMinerals.visibility = View.GONE
            }
            else if (isOpaque){
                mineralName.text = opaqueMineral?.nom
                if (opaqueMineral?.formula != null)
                    mineralChemicalFormula.text = Html.fromHtml(opaqueMineral.formula, HtmlCompat.FROM_HTML_MODE_LEGACY)
                else
                    mineralChemicalFormula.visibility = View.GONE
                mineralColors.text = opaqueMineral?.colors
                mineralPleochroism.text = opaqueMineral?.pleocroisme
                mineralReflectivity.text = opaqueMineral?.reflectivitat
                mineralHardness.text = opaqueMineral?.resistencia_polit
                mineralAnisotropy.text = opaqueMineral?.anisotropia
                mineralInterferenceColors.text = opaqueMineral?.colors_interferencia
                mineralInternalReflections.text = opaqueMineral?.reflexions_internes
                mineralCleavage.text = opaqueMineral?.exfoliacio_polit
                mineralAssociatedMinerals.text = opaqueMineral?.minerals_associats
                val principalAdapter = SamplesListAdapter(true)
                viewModel.samplesOfOpaqueMineralListLD.observe(viewLifecycleOwner,{principalAdapter.setOpaqueMineralSamplesList(
                    it.filter { min -> min.id_mineral == id } as MutableList<OpaqueMineralSample>) })
                recyclerView.adapter = principalAdapter

                //LAYOUT UPDATE
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
